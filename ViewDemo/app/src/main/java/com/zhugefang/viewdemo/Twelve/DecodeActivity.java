package com.zhugefang.viewdemo.Twelve;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.disklrucache.DiskLruCache;
import com.zhugefang.viewdemo.R;
import com.zhugefang.viewdemo.Third.BaseActivity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 起因：防止内存溢出
 * 通过采样率即可有效地加载图片，获取采样率
 * 1、BitmapFactory.Options的inJustDecodeBounds=true
 * 2、从BitmapFactory.Options中取出图片的原始宽高信息，他们对应于outWidth和outHeight参数
 * 3、根据采样的规则并结合目标view的所需大小计算出采样率inSampleSize
 * 4、将bitmapFactory.options的inJUstDecodeBounde=false，并输出图片
 * http://blog.csdn.net/yezhouyong/article/details/50402022
 * //缓存
 * http://blog.csdn.net/qq_33689414/article/details/52370903
 */
public class DecodeActivity extends BaseActivity {
    @Bind(R.id.iv_read)
    ImageView iv_read;
    @Bind(R.id.tv_disLruche)
    TextView tv_disLruche;
    private ImageView imageView;
    private Bitmap bitmap;
    private LruCache<String, Bitmap> lruCache;
    private DiskLruCache diskLruCache;
    private TextView tv_downImg;
    String imageUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
    DownHandle handle = new DownHandle(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageView = ((ImageView) findViewById(R.id.iv));
        tv_downImg = ((TextView) findViewById(R.id.tv_down));
        bitmap = decodeSampledBitmapFormResource();
        imageView.setImageBitmap(bitmap);
        //内存缓存
//        int maxMemory = ((int) Runtime.getRuntime().maxMemory());
//        int cacheSize = maxMemory / 8;
//        LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(cacheSize) {
//            @Override
//            protected int sizeOf(String key, Bitmap bitmap) {
//                return bitmap.getByteCount();
//            }
//
//            @Override
//            protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
//                super.entryRemoved(evicted, key, oldValue, newValue);
//            }
//        };
//        lruCache.put("bitmap", bitmap);
        initDisLruch();
    }

    private void initDisLruch() {
        //硬盘缓存
        try {
            File file = getCacheDir(DecodeActivity.this, "bitmap");
            if (!file.exists()) {
                file.mkdirs();
            }
            diskLruCache = DiskLruCache.open(file, getAppVersion(), 1, 10 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tv_disLruche.setText("缓存大小：" + diskLruCache.size());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_decode;
    }

    @OnClick({R.id.tv_down, R.id.tv_read, R.id.tv_remove, R.id.tv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_delete:
                if (diskLruCache != null) {
                    try {
//                        关闭掉了之后就不能再调用DiskLruCache中任何操作缓存数据的方法
                        diskLruCache.delete();//写入器会被关掉，不能再执行下载操作
                        tv_disLruche.setText("缓存大小：" + diskLruCache.size());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.tv_remove:
                if (diskLruCache != null) {
                    String imgKey = hashKeyForrDisk(imageUrl);
                    try {
                        diskLruCache.remove(imgKey);
                        tv_disLruche.setText("缓存大小：" + diskLruCache.size());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                break;
            case R.id.tv_down:
                downImg();
                tv_disLruche.setText("缓存大小：" + diskLruCache.size());
                break;
            case R.id.tv_read:
                if (diskLruCache != null) {
                    String key = hashKeyForrDisk(imageUrl);
                    try {
                        DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
                        if (snapshot != null) {
                            FileInputStream inputStream = ((FileInputStream) snapshot.getInputStream(0));
//                            这种方式是加载原图，容易oom
//                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            //这种是加载缩放后的图,通过流得到文件描述
                            FileDescriptor fd = inputStream.getFD();
                            Bitmap bitmap = decodeSimpledBitmapFromFd(fd, 300, 300);
                            if (bitmap != null)
                                iv_read.setImageBitmap(bitmap);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                break;

        }

    }

    public Bitmap decodeSimpledBitmapFromFd(FileDescriptor fd, int reqWith, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fd, null, options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateInSimpleSize(options, reqWith, reqHeight);
        Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fd, null, options);
        return bitmap;
    }

    private int calculateInSimpleSize(BitmapFactory.Options options, int reqWith, int reqHeight) {
        if (reqWith == 0 || reqHeight == 0) return 1;
        int outHeight = options.outHeight;
        int outWidth = options.outWidth;
        int simpleSize = 1;
        if (outHeight > reqHeight || outWidth > reqWith) {
            int halfHeight = outHeight / 2;
            int halfWidth = outWidth / 2;
            while ((halfHeight / simpleSize) >= reqHeight && (halfWidth / simpleSize) >= reqWith) {
                simpleSize *= 2;
            }
        }
        return simpleSize;
    }

    private void downImg() {
        showToast("下载开始");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String key = hashKeyForrDisk(imageUrl);
                    DiskLruCache.Editor editor = diskLruCache.edit(key);
                    if (editor != null) {
                        if (downUrlToStream(imageUrl, editor.newOutputStream(0))) {
                            editor.commit();
                        } else {
                            editor.abort();
                        }
                    }
                    diskLruCache.flush();
                    handle.sendMessage(Message.obtain());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    private static class DownHandle extends Handler {
        public WeakReference<DecodeActivity> weakReference;

        public DownHandle(DecodeActivity activity) {
            weakReference = new WeakReference<DecodeActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            DecodeActivity activity = weakReference.get();
            if (activity == null || activity.isFinishing()) return;
            Toast.makeText(activity, "下载成功！", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean downUrlToStream(String urlString, OutputStream outputStream) {
        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            out = new BufferedOutputStream(outputStream, 8 * 1024);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (diskLruCache != null) {
            try {
//                关闭掉了之后就不能再调用DiskLruCache中任何操作缓存数据的方法
                diskLruCache.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
        *key是： 缓存文件的文件名，并且必须要和图片的URL是一一对应的
        * */
    private String hashKeyForrDisk(String key) {
        String cacheKey;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(key.getBytes());//key.getBytes()长度是60，md5.digest()，随机的-24,110,134，。。。。
            cacheKey = HexString(md5.digest());//e37775b7868532e0d2986b1ff384c078
        } catch (Exception e) {
            cacheKey = String.valueOf(key.hashCode());////41067621
        }
        return cacheKey;
    }

    /*转换成16进制的*/
    private String HexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xff & bytes[i]);//e3
            if (hex.length() == 1) {
                builder.append("0");
            }
            builder.append(hex);
        }
        return builder.toString();
    }

    public int getAppVersion() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /*
    * 可以看到，当SD卡存在或者SD卡不可被移除的时候，就调用getExternalCacheDir()方法来获取缓存路径，
    * 否则就调用getCacheDir()方法来获取缓存路径。前者获取到的就是 /sdcard/Android/data/<application package>/cache 这个路径，
    * 而后者获取到的是 /data/data/<application package>/cache 这个路径。
    * */
    public File getCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    private Bitmap decodeSampledBitmapFormResource() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.mipmap.zhuge, options);
        int inSimpleSize = calculateINsamplesSize(options, 40, 40);
        options.inSampleSize = inSimpleSize;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.zhuge, options);
        return bitmap;
    }

    public static int calculateINsamplesSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int outHeight = options.outHeight;
        int outWidth = options.outWidth;
        Log.d("DecodeActivity", "outHeight: " + outHeight + "----outWidth:" + outWidth);
        int inSimpleSize = 1;
        if (outHeight > reqHeight || outWidth > reqWidth) {
            int halfHeight = outHeight / 2;
            int halfWidth = outWidth / 2;
            while ((halfHeight / inSimpleSize) >= reqHeight && (halfWidth / inSimpleSize) >= reqWidth) {
                inSimpleSize *= 2;
            }
        }
        return inSimpleSize;
    }

    public void lruCache() {
        int maxMemory = ((int) (Runtime.getRuntime().maxMemory() / 1024));
        int cacheSize = maxMemory / 8;
        lruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (diskLruCache != null) {
            try {
                diskLruCache.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class Text<T> {
        <T> void func(T val) {
        }
    }

}

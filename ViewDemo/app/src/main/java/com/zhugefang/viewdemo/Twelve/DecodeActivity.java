package com.zhugefang.viewdemo.Twelve;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.disklrucache.DiskLruCache;
import com.zhugefang.viewdemo.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
public class DecodeActivity extends AppCompatActivity {
    private ImageView imageView;
    private Bitmap bitmap;
    private LruCache<String, Bitmap> lruCache;
    private DiskLruCache diskLruCache;
    private TextView tv_downImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decode);
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

    }

    @OnClick({R.id.tv_down})
    public void onClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //硬盘缓存
                    try {
                        File file = getCacheDir(DecodeActivity.this, "bitmap");
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        diskLruCache = DiskLruCache.open(file, getAppVersion(), 1, 10l);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String imageUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
                    String key = hashKeyForrDisk(imageUrl);
                    DiskLruCache.Editor editor = diskLruCache.edit(key);
                    if (editor != null) {
                        if (downUrlToStream(imageUrl, editor.newOutputStream(0))) {
                            editor.commit();
                        }else {
                            editor.abort();
                        }
                    }
                    diskLruCache.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
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

    /*
    *key是： 缓存文件的文件名，并且必须要和图片的URL是一一对应的
    * */
    private String hashKeyForrDisk(String key) {
        String cacheKey;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(key.getBytes());
            cacheKey = HexString(md5.digest());
        } catch (Exception e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private String HexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xff & bytes[i]);
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

    public class Text<T> {
        <T> void func(T val) {
        }
    }

}

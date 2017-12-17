package com.zhugefang.viewdemo.Twelve;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.jakewharton.disklrucache.DiskLruCache;
import com.jakewharton.disklrucache.Util;
import com.zhugefang.viewdemo.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.PhantomReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by chunLin on 2017/12/10.
 */

public class ImageLoader {
    private static final String TAG = "ImageLoader";
    public static final int MESSAGE_POST_RESULT = 1;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final long keep_alive = 10L;
    private static final int tag_key_uri = R.id.imageloader_uri;
    private static final int disk_cache_size = 50 * 1024 * 1024;
    private static final int io_buffer_size = 8 * 1024;
    private static final int disk_cache_index = 0;
    private boolean mIsDiskLruCacheCreated = false;
    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        //高并发下，线程安全地操作integer
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "ImageLoader#" + mCount.getAndIncrement());
        }

    };
    public static final Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, keep_alive, TimeUnit.SECONDS
            , new LinkedBlockingDeque<Runnable>(), sThreadFactory);

    Handler mMainHand = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            LoaderResult result = (LoaderResult) msg.obj;
            ImageView img = result.img;
            String uri = (String) img.getTag(tag_key_uri);
            if (uri.equals(result.url)) {
                img.setImageBitmap(result.bitmap);
            } else {
                Log.d(TAG, "handleMessage: set image bitMap,but url has changed,ignored!");
            }
        }
    };
    private Context mContext;
    private ImageResizer imageResizer = new ImageResizer();
    private LruCache<String, Bitmap> mMemoryCache;
    private DiskLruCache diskLruCache;

    private ImageLoader(Context context) {
        mContext = context.getApplicationContext();
        int maxMemory = ((int) (Runtime.getRuntime().maxMemory() / 1024));
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
        File diskCacheDir = getDiskCacheDir(mContext, "bitmap");
        if (!diskCacheDir.exists()) {
            diskCacheDir.mkdirs();
        }
        if (getUsableSpace(diskCacheDir) >= disk_cache_size) {
            try {
                diskLruCache = DiskLruCache.open(diskCacheDir, 1, 1, disk_cache_size);
                mIsDiskLruCacheCreated = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ImageLoader build(Context context) {
        return new ImageLoader(context);
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemoryCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemoryCache(String key) {
        return mMemoryCache.get(key);
    }

    public void bindBitmap(final String uri, final ImageView imageView) {
        bindBitmap(uri, imageView, 0, 0);
    }

    private void bindBitmap(final String uri, final ImageView imageView, final int reqWidth, final int reqHeight) {
        imageView.setTag(R.id.imageloader_uri, uri);
        Bitmap bitmap = loadBitmapFromMemCache(uri);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        Runnable loadBitmapTask = new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = loadBitmap(uri, reqWidth, reqHeight);
                if (bitmap != null) {
                    LoaderResult result = new LoaderResult(imageView, uri, bitmap);
                    mMainHand.obtainMessage(MESSAGE_POST_RESULT, result).sendToTarget();
                }
            }
        };
        THREAD_POOL_EXECUTOR.execute(loadBitmapTask);
    }

    /**
     * @param uri
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public Bitmap loadBitmap(String uri, int reqWidth, int reqHeight) {
        Bitmap bitmap = loadBitmapFromMemCache(uri);
        if (bitmap != null) {
            return bitmap;
        }
        bitmap = loadBitmapFromDiskCache(uri, reqWidth, reqHeight);
        if (bitmap != null) {
            return bitmap;
        }
        bitmap = loadBitmapFromHttp(uri, reqWidth, reqHeight);
        if (bitmap == null && !mIsDiskLruCacheCreated) {
            bitmap = downloadBitmapFromUrl(uri);
        }
        return bitmap;
    }

    public Bitmap downloadBitmapFromUrl(String uri) {
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        try {
            URL url = new URL(uri);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), disk_cache_size);
            return BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private Bitmap loadBitmapFromHttp(String uri, int reqWidth, int reqHeight) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new RuntimeException("can not visit network from ui Thread");
        }
        String key = hashKeyFromUrl(uri);
        if (diskLruCache == null)
            return null;
        try {
            DiskLruCache.Editor editor = diskLruCache.edit(key);
            if (editor != null) {
                OutputStream outputStream = editor.newOutputStream(disk_cache_index);
                if (downloadUrlToStream(uri, outputStream)) {
                    editor.commit();
                } else {
                    editor.abort();
                }
            }
            diskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadBitmapFromDiskCache(uri, reqWidth, reqHeight);
    }

    public boolean downloadUrlToStream(String urlString, OutputStream outputStream) {
        HttpURLConnection urlConnection = null;
        BufferedInputStream input = null;
        BufferedOutputStream out = null;
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            input = new BufferedInputStream(urlConnection.getInputStream(), io_buffer_size);//connected = false;
            out = new BufferedOutputStream(outputStream, io_buffer_size);
//            有了input,out不需要字节byte
            int b;
            while ((b = input.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();//connected = false;
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    private Bitmap loadBitmapFromDiskCache(String uri, int reqWidth, int reqHeight) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.w(TAG, "load bitmap from UI thread,it is not recommened!");
        }
        if (diskLruCache == null) return null;
        Bitmap bitmap = null;
        String key = hashKeyFromUrl(uri);
        try {
            DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
            if (snapshot != null) {
                FileInputStream inputStream = (FileInputStream) snapshot.getInputStream(disk_cache_index);
                FileDescriptor fd = inputStream.getFD();
                bitmap = imageResizer.decodeFromFileDescripe(fd, reqWidth, reqHeight);
                if (bitmap != null) {
                    addBitmapToMemoryCache(key, bitmap);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;

    }

    private Bitmap loadBitmapFromMemCache(String uri) {
        final String key = hashKeyFromUrl(uri);
        return getBitmapFromMemoryCache(key);
    }

    private String hashKeyFromUrl(String uri) {
        String key;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(uri.getBytes());
            key = toHexString(md5.digest());
        } catch (Exception e) {
            key = String.valueOf(uri.hashCode());

        }
        return key;
    }

    public String toHexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hexString = Integer.toHexString(0xff & bytes[i]);
            if (hexString.length() == 1) {
                builder.append("0");
            }
            builder.append(hexString);
        }
        return builder.toString();
    }

    public long getUsableSpace(File diskCacheDir) {
        long usableSpace = diskCacheDir.getUsableSpace();
        return usableSpace;
    }

    private File getDiskCacheDir(Context mContext, String bitmap) {
        String filePath = "";
        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            filePath = mContext.getExternalCacheDir().getPath();//:sdcard/android/包名/cache
        } else {
            filePath = mContext.getCacheDir().getPath();//data/data/包名/cache
        }
        return new File(filePath + File.separator + bitmap);
    }

    private static class LoaderResult {
        private ImageView img;
        private String url;
        private Bitmap bitmap;

        public LoaderResult(ImageView img, String url, Bitmap bitmap) {
            this.img = img;
            this.url = url;
            this.bitmap = bitmap;
        }
    }
}

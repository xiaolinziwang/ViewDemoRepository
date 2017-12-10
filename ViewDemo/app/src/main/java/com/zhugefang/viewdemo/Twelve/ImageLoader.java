package com.zhugefang.viewdemo.Twelve;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.jakewharton.disklrucache.DiskLruCache;

import java.lang.ref.PhantomReference;
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
    private static final int tag_key_uri = ;
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

    Handler sMainHand = new Handler(Looper.myLooper()) {
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
        int maxMemoty = ((int) (Runtime.getRuntime().maxMemory() / 8));
        mMemoryCache = new LruCache<String,Bitmap>(maxMemoty) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }
        };
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

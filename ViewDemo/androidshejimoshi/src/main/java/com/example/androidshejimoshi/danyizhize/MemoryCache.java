package com.example.androidshejimoshi.danyizhize;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2019/11/7 上午11:59
 * 修改人：chunlinwang
 * 修改时间：2019/11/7 上午11:59
 * 修改备注：
 */
public class MemoryCache implements ImageCache {
    String TAG = "MemoryCache";
    private LruCache<String, Bitmap> mImageCache;


    public MemoryCache() {
        initImageCache();
    }


    private void initImageCache() {
        //计算可使用的最大内存
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        Log.d(TAG, "initImageCache:--maxMemory--" + maxMemory);
        //取四分之一做缓存
        int cacheSize = maxMemory / 4;
        Log.d(TAG, "initImageCache: --cacheSize--" + cacheSize);
        mImageCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }


    @Override
    public void put(String url, Bitmap bitmap) {
        mImageCache.put(url, bitmap);
    }


    @Override
    public Bitmap get(String url) {
        return mImageCache.get(url);
    }
}

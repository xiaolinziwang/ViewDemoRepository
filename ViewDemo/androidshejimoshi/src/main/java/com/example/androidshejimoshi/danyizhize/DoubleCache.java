package com.example.androidshejimoshi.danyizhize;

import android.graphics.Bitmap;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2019-11-08 13:34
 * 修改人：chunlinwang
 * 修改时间：2019-11-08 13:34
 * 修改备注：
 */
public class DoubleCache {
    MemoryCache mMemoryCache = new MemoryCache();
    DiskCache mDiskCache = new DiskCache();


    public Bitmap get(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
        if (bitmap == null) {
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }


    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url, bitmap);
        mDiskCache.put(url, bitmap);
    }
}

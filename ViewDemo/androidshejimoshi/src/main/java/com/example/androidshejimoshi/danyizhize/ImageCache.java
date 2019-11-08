package com.example.androidshejimoshi.danyizhize;

import android.graphics.Bitmap;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2019-11-08 13:49
 * 修改人：chunlinwang
 * 修改时间：2019-11-08 13:49
 * 修改备注：
 */
public interface ImageCache {
    public Bitmap get(String url);

    public void put(String url, Bitmap bmp);
}

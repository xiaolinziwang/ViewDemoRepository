package com.example.androidshejimoshi.danyizhize;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2019/11/7 上午11:31
 * 修改人：chunlinwang
 * 修改时间：2019/11/7 上午11:31
 * 修改备注：
 */
public class ImageLoader {

    private Bitmap bitmap;
    private final ExecutorService mExecutorService;
    private DoubleCache mDoubleCache;


    public ImageLoader() {
        mExecutorService = new

                ThreadPoolExecutor(5, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
        mDoubleCache = new DoubleCache();
    }


    public void displayImage(final String url, final ImageView imageView) {
        Bitmap bitmap = mDoubleCache.get(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Log.d("ImageLoader", "run: " + Thread.currentThread().getName());
                Bitmap bitmap = downloadImage(url);
                if (bitmap == null) {
                    return;
                }
                if (imageView.getTag().equals(url)) {
                    imageView.setImageBitmap(bitmap);
                }
                mDoubleCache.put(url, bitmap);
            }
        });
    }


    private Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}

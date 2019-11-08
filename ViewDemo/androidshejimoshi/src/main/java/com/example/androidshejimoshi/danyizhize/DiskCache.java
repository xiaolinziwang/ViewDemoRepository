package com.example.androidshejimoshi.danyizhize;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2019/11/7 下午2:00
 * 修改人：chunlinwang
 * 修改时间：2019/11/7 下午2:00
 * 修改备注：
 */
public class DiskCache implements ImageCache {
    static String cacheDir = "sdcard/image_cache.png";


    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = BitmapFactory.decodeFile(cacheDir);
        Log.d("sdcard", "get: --" + bitmap);
        return bitmap;
    }


    @Override
    public void put(String url, Bitmap bmp) {
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(cacheDir);
            if (!file.exists()) {
                //先得到文件的上级目录，并创建上级目录，在创建文件
                file.getParentFile().mkdir();
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

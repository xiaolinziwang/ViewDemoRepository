package com.zhugefang.viewdemo.Twelve;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileDescriptor;

/**
 * Created by chunLin on 2017/12/11.
 */

public class ImageResizer {
    private static final String TAG = "ImageResizer";

    public Bitmap decodeFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = decodeInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
    public Bitmap decodeFromFileDescripe(FileDescriptor fd, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fd, null, options);
        options.inSampleSize = decodeInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fd, null, options);
    }

    private int decodeInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        if (reqHeight == 0 || reqWidth == 0) {
            return 1;
        }
        int outHeight = options.outHeight;
        int outWidth = options.outWidth;
        int halfHeight = outHeight / 2;
        int halfWidth = outWidth / 2;
        int inSimpleSize = 1;
        if ((halfHeight / inSimpleSize) > reqHeight && (halfWidth / inSimpleSize) > reqWidth) {
            inSimpleSize *= 2;
        }
        return inSimpleSize;
    }
}

package com.zhugefang.viewdemo.Ten;

import android.util.Log;

/**
 * Created by chunLin on 2018/1/7.
 */

public class ThreadLocalClass {
    public String TAG = this.getClass().getSimpleName();

    public static void main(String[] args) {
        final ThreadLocal<Boolean> mBooleanThreadLocal = new ThreadLocal<>();
        mBooleanThreadLocal.set(true);
        mBooleanThreadLocal.set(false);
        System.out.println(Thread.currentThread().getName() + ":" + mBooleanThreadLocal.get());
    }
}

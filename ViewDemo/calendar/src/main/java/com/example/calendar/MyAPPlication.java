package com.example.calendar;

import android.app.Application;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2019/5/16 下午2:05
 * 修改人：chunlinwang
 * 修改时间：2019/5/16 下午2:05
 * 修改备注：
 */
public class MyAPPlication extends Application {

    private static MyAPPlication instance;


    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }


    public static MyAPPlication getInstance() {
        return instance;
    }
}

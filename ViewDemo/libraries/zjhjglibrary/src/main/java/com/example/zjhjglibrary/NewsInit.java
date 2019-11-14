package com.example.zjhjglibrary;

import android.app.Application;
import android.util.Log;
import com.example.basezjhjg.BaseAppInt;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2019-11-14 14:10
 * 修改人：chunlinwang
 * 修改时间：2019-11-14 14:10
 * 修改备注：
 */
public class NewsInit implements BaseAppInt {
    @Override
    public boolean onInitSpeed(Application application) {
        Log.d("BaseAppInt", "NewsInit--onInitSpeed: ");
        return false;
    }


    @Override
    public boolean onInitLow(Application application) {
        Log.d("BaseAppInt", "NewsInit--onInitLow: ");
        return false;
    }
}

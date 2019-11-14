package com.example.basezjhjg;

import android.app.Application;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2019-11-14 12:25
 * 修改人：chunlinwang
 * 修改时间：2019-11-14 12:25
 * 修改备注：
 */
public interface BaseAppInt {
    boolean onInitSpeed(Application application);
    boolean onInitLow(Application application);
}

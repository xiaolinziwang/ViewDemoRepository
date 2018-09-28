package com.shuidihuzhu.factory.method;

import android.util.Log;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2018/9/25 下午4:43
 * 修改人：chunlinwang
 * 修改时间：2018/9/25 下午4:43
 * 修改备注：
 */
public class Bus implements Car{
    @Override
    public void gotowork() {
        Log.d("Bus", "gotowork: 坐Bus去上班");
    }
}

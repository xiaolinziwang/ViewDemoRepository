package com.example.zujianhuajiagou;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2019/5/10 下午4:06
 * 修改人：chunlinwang
 * 修改时间：2019/5/10 下午4:06
 * 修改备注：
 */
public class LocalBroadcastReceiver extends BroadcastReceiver {
    String TAG = "LocalBroadcastReceiver";


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: " + "I LOVE Wangchunlin");
    }
}

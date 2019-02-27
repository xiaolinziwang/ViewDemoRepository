package com.chunlin.viewdemo.Eleven;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chunlin.viewdemo.R;
import com.chunlin.viewdemo.Third.BaseActivity;

/*
*https://www.cnblogs.com/tinyphp/p/3768214.html
* "=="与equals：
* “==”：用于基本数据类型的比较；判断引用是否指向堆内存的同一块地址
* equals:2个变量是否是对同一个对象的引用，即堆中的内容是否相同
*
*
* */
public class IntentServiceActivity extends BaseActivity {
    public static final String UPLOAD_RESULT = "UPLOAD_RESULT";
    private LinearLayout mLyTaskContainer;
    private BroadcastReceiver uploadImgReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {//该方法不能做耗时操作，只是做一个转接
            if (intent.getAction() == UPLOAD_RESULT) {
                String path = intent.getStringExtra(UploadImgService.EXTRA_IMG_PATH);
                handleResult(path);
            }
        }
    };


    private void handleResult(String path) {
        TextView tv = (TextView) mLyTaskContainer.findViewWithTag(path);
        tv.setText(path + "upload success ~~~~~~~");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLyTaskContainer = ((LinearLayout) findViewById(R.id.id_ll_taskcontainer));
        registerReceiver();
    }


    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UPLOAD_RESULT);
        registerReceiver(uploadImgReceiver, filter);
    }


    int mInt = 0;


    public void addTask(View view) {
        //模拟下载路径
        String path = "/sdcard/imgs/" + (++mInt) + ".png";
        UploadImgService.startUploadImg(this, path);
        TextView textView = new TextView(this);
        mLyTaskContainer.addView(textView);
        textView.setText(path + "is uploading...");
        textView.setTag(path);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(uploadImgReceiver);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_intent_service;
    }
}

package com.example.zujianhuajiagou;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewById(R.id.btn_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ZJHJGActivity.startActivity(Man2Activity.this);
                //LocalBrodcastReceiverActivity.startActivity(Main2Activity.this);
                //hideIntent();
                hideIntentCom();
            }
        });
    }


    private void hideIntentCom() {
        Intent intent = new Intent();
        intent.setClassName("com.example.zujianhuajiagou", "com.example.zjhjglibrary.ZJHJGActivity");
        //intent.setComponent(new ComponentName("com.example.zujianhuajiagou", "com.example.zjhjglibrary.ZJHJGActivity"));
        //intent.setComponent(new ComponentName("com.example.zjhjglibrary", "com.example.zjhjglibrary.ZJHJGActivity"));
        if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) !=
                null) {
            startActivity(intent);
        }
    }


    /**
     * 备注人：chunlinwang
     * 备注时间：2019/6/4 下午3:54
     * 备注：接受Activity必须在清单文件中：
     * <action android:name="zjhjglibrary"/>
     * <category android:name="android.intent.category.DEFAULT"/>
     * 如果不写category，会报如下错误：
     * android.content.ActivityNotFoundException: No Activity found to handle Intent { act=zjhjglibrary }
     */

    private void hideIntent() {
        Intent intent = new Intent("zjhjglibrary");
        startActivity(intent);
    }
}

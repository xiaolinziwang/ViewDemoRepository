package com.example.buriedpoint;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.sdk.SensorsDataAPI;

public class MainActivity extends AppCompatActivity {
    private final static int PERMISSIONS_REQUEST_READ_CONTACTS = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main2Activity.startActivity(MainActivity.this);
            }
        });
        setTitle("Home");

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) ==
                PackageManager.PERMISSION_GRANTED) {
            //拥有权限
        } else {
            //没有权限, 需要申请全新啊
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.READ_CONTACTS }, PERMISSIONS_REQUEST_READ_CONTACTS);
        }
    }
/**
* 备注人：chunlinwang
* 备注时间：2019-12-27 14:33
* 备注：  通过测试可以发现，我们调用ActivityCompat.requestPermissions方法申请权限之后，
 * 不管用户选择了“允许”还是“禁止”按钮，系统都会先调用onRequestPermissionsResult回调方法，
 * 然后再调用当前Activity的onResume生命周期函数。而我们上面介绍的，
 * 就是通过onResume生命周期函数来采集页面浏览事件的，
 * 这个现象会直接导致我们的埋点SDK再一次触发页面浏览事件。
 *
 * 摘自：《Android全埋点解决方案》 — 王灼洲
 * 在豆瓣阅读书店查看：https://read.douban.com/ebook/110275594/
 * 本作品由华章数媒授权豆瓣阅读全球范围内电子版制作与发行。
 * © 版权所有，侵权必究。
*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        SensorsDataAPI.getInstance().ignoreAutoTrackActivity(MainActivity.class);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_READ_CONTACTS:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 用户点击允许
                } else {
                    // 用户点击禁止
                }
                break;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onStop() {
        super.onStop();
        SensorsDataAPI.getInstance().removeIgnoredActivity(MainActivity.class);
    }
}



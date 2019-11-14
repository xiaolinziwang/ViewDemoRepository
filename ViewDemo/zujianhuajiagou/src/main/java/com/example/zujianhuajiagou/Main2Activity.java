package com.example.zujianhuajiagou;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import java.util.List;

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
                //hideIntentCom();
                //requestPermission();
                dysnPermission();
            }
        });
    }


    /**
     * 备注人：chunlinwang
     * 备注时间：2019-11-14 17:01
     * 备注：动态权限
     */
    private void dysnPermission() {
        String camera = Manifest.permission.CAMERA;
        AndPermission.with(this).runtime().permission(camera).onGranted(new Action<List<String>>() {
            @Override
            public void onAction(List<String> data) {
                List<String> data1 = data;
                Toast.makeText(Main2Activity.this, "被用户同意", Toast.LENGTH_LONG).show();
            }
        }).onDenied(new Action<List<String>>() {
            @Override
            public void onAction(List<String> data) {
                List<String> data1 = data;
                Toast.makeText(Main2Activity.this, "被用户决绝", Toast.LENGTH_LONG).show();
            }
        }).start();
    }


    private void requestPermission() {
        boolean isMinSdkM = Build.VERSION.SDK_INT < Build.VERSION_CODES.M;
        if (!isMinSdkM && ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat
                    .shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                Toast.makeText(this, "被用户拒绝", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat
                        .requestPermissions(this, new String[] { Manifest.permission.CAMERA }, 1);
            }
        } else {
            Toast.makeText(this, "获得了权限", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "获得了权限", Toast.LENGTH_LONG).show();
            Log.d("Permission", "onRequestPermissionsResult: 获得了权限");
        }
        if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
            Log.d("Permission", "onRequestPermissionsResult: 拒绝了该权限");
        }
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
     * android.content.ActivityNotFoundException: No Activity found to handle Intent {
     * act=zjhjglibrary }
     */

    private void hideIntent() {
        Intent intent = new Intent("zjhjglibrary");
        startActivity(intent);
    }
}

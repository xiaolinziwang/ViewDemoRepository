package com.zhugefang.viewdemo.Five;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

import com.zhugefang.viewdemo.R;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        NotificationManager notifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        //设置小图标
//       .setSmallIcon(R.drawable.zhuge)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.zhuge))
        builder.setContentTitle("最简单的notification")
                .setContentText("只有小图标、标题、内容")
                .setWhen(System.currentTimeMillis());
        notifyManager.notify(1, builder.build());

    }
}

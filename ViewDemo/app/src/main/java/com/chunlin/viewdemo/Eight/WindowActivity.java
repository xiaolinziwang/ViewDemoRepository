package com.chunlin.viewdemo.Eight;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.chunlin.viewdemo.R;

public class WindowActivity extends AppCompatActivity {
    String TAG = "WindowActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(intent, 1);
            }
        }
        setContentView(R.layout.activity_window);
        //WindowManager.LayoutParams是继承自ViewGroup.LayoutParams,Window是一个抽象的类，不能new,
        //所以添加view的时候，都是2个参数，view和params的形式
        final TextView button = new TextView(this);
        button.setText("button");
        button.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        final WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 0, PixelFormat.TRANSPARENT);
        //flag设置window属性
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;//onTouch事件可以，onClick事件不可以
        // type 设置 Window 类别（层级），这个决定是window中的哪三样：应用window\子window\系统window
        layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
//        layoutParams.gravity = Gravity.TOP;
        final WindowManager windowManager = getWindowManager();
        windowManager.addView(button, layoutParams);
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                Toast.makeText(WindowActivity.this, "button", Toast.LENGTH_LONG).show();
//                layoutParams.y=100;
//                layoutParams.x=800;
//                windowManager.updateViewLayout(button,layoutParams);
                windowManager.removeView(button);
                float rawX = event.getRawX();
                float rawY = event.getRawY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        layoutParams.x = ((int) rawX);
                        layoutParams.y = ((int) rawY);
                        Log.d(TAG, "onTouch: layoutParams.x--"+layoutParams.x+"--rawX--"+rawX);
                        Log.d(TAG, "onTouch: layoutParams.y--"+layoutParams.y+"--rawY--"+rawY);
                        windowManager.updateViewLayout(button, layoutParams);
                        break;
                }
//                Toast.makeText(WindowActivity.this, "button", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}

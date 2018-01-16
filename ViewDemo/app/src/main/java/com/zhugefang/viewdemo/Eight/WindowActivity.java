package com.zhugefang.viewdemo.Eight;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;
import com.zhugefang.viewdemo.R;

public class WindowActivity extends AppCompatActivity {

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
        TextView button = new TextView(this);
        button.setText("button");
        button.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(500,500, 0, 0, PixelFormat.TRANSPARENT);
        //flag设置window属性
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        // type 设置 Window 类别（层级），这个决定是window中的哪三样：应用window\子window\系统window
        layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
        layoutParams.gravity = Gravity.CENTER;
        WindowManager windowManager = getWindowManager();
        windowManager.addView(button, layoutParams);
    }
}

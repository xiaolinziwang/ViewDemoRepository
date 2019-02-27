package com.chunlin.viewdemo.Third;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.chunlin.viewdemo.R;
import com.chunlin.viewdemo.Third.widget.TextButton;
import com.chunlin.viewdemo.Third.widget.TextLinearLayout;
/*
* ACTION_DOWN             = 0;
* ACTION_UP               = 1;
* ACTION_MOVE             = 2;
* ACTION_CANCEL           = 3;
* http://blog.csdn.net/yanbober/article/details/45932123
* Android触摸屏事件派发机制详解与源码分析二(Activity篇)
* */
public class DispatchActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {
    private String TAG = "test";
    private TextButton btn;
    private TextLinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn = ((TextButton) findViewById(R.id.btn));
        layout = ((TextLinearLayout) findViewById(R.id.layout));
        btn.setOnClickListener(this);
        btn.setOnTouchListener(this);

        layout.setOnClickListener(this);
        layout.setOnTouchListener(this);
    }

    /*消耗当前事件*/
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "MainActivity--dispatchTouchEvent--action=" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onUserInteraction() {
        Log.i(TAG, "MainActivity--onUserInteraction");
        super.onUserInteraction();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "MainActivity--onTouchEvent--action=" + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dispatch;
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick----v=" + v);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d(TAG, "onTouch--action=" + event.getAction() + "--v=" + v);
        return false;
    }
}

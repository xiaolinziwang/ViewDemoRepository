package com.zhugefang.viewdemo.Third.widget;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.zhugefang.viewdemo.R;
import com.zhugefang.viewdemo.Third.BaseActivity;

public class DispatchView2Activity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {
    private String TAG = "test";
    private View layout;
    private TextButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = ((View) findViewById(R.id.layout));
        btn = ((TextButton) findViewById(R.id.btn));

        layout.setOnClickListener(this);
        layout.setOnTouchListener(this);

        btn.setOnClickListener(this);
        btn.setOnTouchListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dispatch_view2;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i("test", "OnTouchListener--onTouch-- action=" + event.getAction() + " --" + v);
        return false;
    }

    @Override
    public void onClick(View v) {
        Log.i("test", "OnClickListener--onClick--" + v);
    }
}

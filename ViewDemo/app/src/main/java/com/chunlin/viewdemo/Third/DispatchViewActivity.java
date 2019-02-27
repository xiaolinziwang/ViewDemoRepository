package com.chunlin.viewdemo.Third;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.chunlin.viewdemo.R;

/**
 *http://blog.csdn.net/yanbober/article/details/45887547
 *  Android触摸屏事件派发机制详解与源码分析二(view篇)
 */
public class DispatchViewActivity extends BaseActivity implements View.OnTouchListener, View.OnClickListener {

    private RelativeLayout layout;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = ((RelativeLayout) findViewById(R.id.activity_dispatch_view));
        btn = ((Button) findViewById(R.id.btn));

        btn.setOnTouchListener(this);
        btn.setOnClickListener(this);

        layout.setOnTouchListener(this);
        layout.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dispatch_view;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i(TAG, "OnTouchListener--onTouch-- action=" + event.getAction() + " --" + v);
        return false;
    }


    @Override
    public void onClick(View v) {
        Log.i(TAG, "OnClickListener--onClick--" + v);
    }
}

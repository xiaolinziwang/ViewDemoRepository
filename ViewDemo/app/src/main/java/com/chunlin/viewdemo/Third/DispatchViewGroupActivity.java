package com.chunlin.viewdemo.Third;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.chunlin.viewdemo.R;
import com.chunlin.viewdemo.Third.widget.TextButton;
import com.chunlin.viewdemo.Third.widget.TextLinearLayout;

/**
 * http://blog.csdn.net/yanbober/article/details/45912661
 * Android触摸屏事件派发机制详解与源码分析二(ViewGroup篇)
 */
public class DispatchViewGroupActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {

    private TextLinearLayout layout;
    private TextButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        layout = ((TextLinearLayout) findViewById(R.id.layout));
        btn = ((TextButton) findViewById(R.id.btn));

        layout.setOnClickListener(this);
        layout.setOnTouchListener(this);

        btn.setOnClickListener(this);
        btn.setOnTouchListener(this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dispatch_view_group;
    }

    @Override
    public void onClick(View v) {
        Log.i("test", "OnClickListener--onClick--" + v);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i("test", "OnTouchListener--onTouch-- action=" + event.getAction() + " --" + v);
        return false;
    }
}

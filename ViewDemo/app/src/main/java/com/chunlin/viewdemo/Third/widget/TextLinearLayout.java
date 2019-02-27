package com.chunlin.viewdemo.Third.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/10/25.
 */

public class TextLinearLayout extends LinearLayout {
    private String TAG = "test";
    public TextLinearLayout(Context context) {
        super(context);
    }

    public TextLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "TestLinearLayout--dispatchTouchEvent--action=" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "TestLinearLayout--onInterceptTouchEvent--action="+ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "TestLinearLayout--onTouchEvent--action="+event.getAction());
        return super.onTouchEvent(event);
    }
}

package com.zhugefang.viewdemo.Third.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by Administrator on 2017/10/25.
 */

public class TextButton extends Button {
    private String TAG = "test";

    public TextButton(Context context) {
        super(context);
    }

    public TextButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i(TAG, "TestButton--dispatchTouchEvent--action=" + event.getAction());
        return super.dispatchTouchEvent(event);
//        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "TestButton--onTouchEvent--action=" + event.getAction());
//        return super.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

}

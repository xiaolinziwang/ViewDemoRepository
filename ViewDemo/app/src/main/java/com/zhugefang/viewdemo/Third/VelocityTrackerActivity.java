package com.zhugefang.viewdemo.Third;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;

import com.zhugefang.viewdemo.R;

public class VelocityTrackerActivity extends BaseActivity {

    private VelocityTracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_velocity_tracker;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (tracker == null) {
            tracker = VelocityTracker.obtain();
        }
        //每一次点击都是一个新的event事件，必须放外面
        tracker.addMovement(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                tracker.computeCurrentVelocity(1000);
                float xVelocity = tracker.getXVelocity();
                float yVelocity = tracker.getYVelocity();
                Log.d(TAG, "onTouchEvent:xVelocity: " + xVelocity + ",yVelocity:" + yVelocity);
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tracker!=null){
            tracker.clear();
            tracker.recycle();
        }
    }
}

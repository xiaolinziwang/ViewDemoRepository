package com.zhugefang.viewdemo.Third;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.zhugefang.viewdemo.R;

/**
 * http://blog.csdn.net/harvic880925/article/details/39520901
 */
public class GestureDetectorActivity extends BaseActivity implements View.OnTouchListener {
    private GestureDetector gestureDetector;
    private TextView viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        gestureDetector = new GestureDetector(new GestrueListener());
        //setOnDoubleTapListener的前提是实现GestureDetector.OnGestureListener,因为构造函数需要
//        gestureDetector.setOnDoubleTapListener(new DoubleListener());
        viewById = ((TextView) findViewById(R.id.tv));
        viewById.setOnTouchListener(this);
//        viewById.setFocusable(true);
        viewById.setClickable(true);//
//        viewById.setLongClickable(true);
        gestureDetector = new GestureDetector(new SimpleListener());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_gesture_detector;
    }

    /*
    *   /*
         * 在onTouch()方法中，我们调用GestureDetector的onTouchEvent()方法，
         * 将捕捉到的MotionEvent交给GestureDetector
         * 来分析是否有合适的callback函数来处理用户的手势
         *
        */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public class GestrueListener implements GestureDetector.OnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            Log.d(TAG, "onDown: ");
            showToast("onDown");
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.d(TAG, "onShowPress: ");
            showToast("onShowPress");
        }

        /*
        前提必须设置：
        * setClickable(true)
        * 执行顺序：ondown()-->onSingleTapUp()
        * */
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.d(TAG, "onSingleTapUp: ");
            showToast("onSingleTapUp");
            return false;
        }

        /*
          前提必须设置：
        * setClickable(true)
        e1：第1个ACTION_DOWN MotionEvent
            e2：最后一个ACTION_MOVE MotionEvent
            在屏幕上拖动事件。无论是用手拖动view，或者是以抛的动作滚动，都会多次触发,这个方法
            在ACTION_MOVE动作发生时就会触发
            滑屏：手指触动屏幕后，稍微滑动后立即松开
            onDown-----》onScroll----》onScroll----》onScroll----》………----->onFling
            拖动
            onDown------》onScroll----》onScroll------》onFiling

            可见，无论是滑屏，还是拖动，影响的只是中间OnScroll触发的数量多少而已，最终都会触发onFling事件！
            distanceX：间隔距离，不是路程
            D/GestrueListener: onScroll:
            D/GestrueListener: onScroll: distanceX:-13.811829,distanceY:-0.5821533
            D/GestrueListener: onScroll:
10-18 16:24:00.458 30217-30217/com.zhugefang.viewdemo D/GestrueListener: onScroll: distanceX:-134.2641,distanceY:-16.21051
10-18 16:24:00.461 30217-30217/com.zhugefang.viewdemo D/GestrueListener: onScroll:
10-18 16:24:00.466 30217-30217/com.zhugefang.viewdemo D/GestrueListener: onScroll: distanceX:-10.776978,distanceY:-1.197937
                      * */
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d(TAG, "onScroll: ");
            showToast("onScroll");
            Log.d(TAG, "onScroll: distanceX:" + distanceX + ",distanceY:" + distanceY);
            showToast("onScroll: distanceX:" + distanceX + ",distanceY:" + distanceY);
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.d(TAG, "onLongPress: ");
            showToast("onLongPress");
        }

        /*
          前提必须设置：
        * setClickable(true)
        * 由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE, 1个ACTION_UP触发
          * velocityX：X轴上的移动速度，像素/秒
            velocityY：Y轴上的移动速度，像素/秒
                滑屏：手指触动屏幕后，稍微滑动后立即松开
            onDown-----》onScroll----》onScroll----》onScroll----》………----->onFling
            拖动
            onDown------》onScroll----》onScroll------》onFiling*/
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d(TAG, "onFling: ");
            showToast("onFling");
            Log.d(TAG, "onFling: velocityX:" + velocityX + ",velocityY:" + velocityY);
            showToast("onFling: velocityX:" + velocityX + ",velocityY:" + velocityY);
            return false;
        }
    }

    public class DoubleListener implements GestureDetector.OnDoubleTapListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.d(TAG, "onSingleTapConfirmed: ");
            showToast("onSingleTapConfirmed");
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.d(TAG, "onDoubleTap: ");
            showToast("onDoubleTap");
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.d(TAG, "onDoubleTapEvent: ");
            showToast("onDoubleTapEvent");
            return false;
        }
    }

    public class SimpleListener extends GestureDetector.SimpleOnGestureListener {
        /*        // 参数解释：
        // e1：第1个ACTION_DOWN MotionEvent
        // e2：最后一个ACTION_MOVE MotionEvent     */
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d(TAG, "onFling: e1.getX():" + e1.getX() + ",e2.getX()" + e2.getX());
            Log.d(TAG, "onFling: e1.getX()-e2.getX():" + (e1.getX() - e2.getX()));
            showToast("onFling: e1.getX():" + e1.getX() + ",e2.getX()" + e2.getX());
            showToast("onFling: e1.getX()-e2.getX():" + (e1.getX() - e2.getX()));//
            Log.d(TAG, "onFling: ScrollX:" + viewById.getScrollX());
            Log.d(TAG, "onFling: Scrolly:" + viewById.getScrollY());

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}

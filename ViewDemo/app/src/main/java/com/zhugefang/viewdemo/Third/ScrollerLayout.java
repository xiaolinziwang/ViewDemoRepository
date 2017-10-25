package com.zhugefang.viewdemo.Third;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by Administrator on 2017/10/18.
 * http://blog.csdn.net/guolin_blog/article/details/48719871
 * Scroller的基本用法其实还是比较简单的，主要可以分为以下几个步骤：
 1. 创建Scroller的实例：只被调用一次
 2. 调用startScroll()方法来初始化滚动数据并刷新界面:只被调用一次
 3. 重写computeScroll()方法，并在其内部完成平滑滚动的逻辑：被调用多次

 */

public class ScrollerLayout extends ViewGroup {
    private String TAG = "ScrollerLayout";
    private final Scroller scroller;
    private final int mTouchSlop;
    private int leftBorder;
    private int rightBorer;
    private float mXDown;
    private float mXLastMove;
    private float mXMove;

    public ScrollerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledDoubleTapSlop();
    }

    /*onmeasure(),就是得到view的测量宽高*/
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
//        measureChildren(widthMeasureSpec,heightMeasureSpec);
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            //为ScrollerLayout中每一个子view测量大小
            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                //layout就是每一个view,是由2个坐标点确定的，layout就是传递进去每一个view的2个坐标点
                childView.layout(i * childView.getMeasuredWidth(), 0,
                        (i + 1) * childView.getMeasuredWidth(), childView.getMeasuredHeight());
            }
            leftBorder = getChildAt(0).getLeft();
            rightBorer = getChildAt(childCount - 1).getRight();
            Log.d(TAG, "onLayout: leftBorder:" + leftBorder + ",rightBorer;" + rightBorer);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mXDown = ev.getRawX();
                mXLastMove = mXDown;
                Log.d(TAG, "onInterceptTouchEvent: ACTION_DOWN-mXDown:" + mXDown + ",mXLastMove:" + mXLastMove);
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = ev.getRawX();
                float diff = Math.abs(mXMove - mXDown);
                mXLastMove = mXMove;
                Log.d(TAG, "onInterceptTouchEvent: ACTION_MOVE-mXMove:" + mXMove + ",mXLastMove:" + mXLastMove +
                        ",diff:" + diff+",mTouchSlop:"+mTouchSlop);
                //当手指拖动值大于TouchSlop值时，认为应该进行滚动，拦截子控件的事件
                if (diff > 6) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mXMove = event.getRawX();
                int scrolledX = ((int) (mXLastMove - mXMove));//手指往左，自然mXLastMove>mXMove
                Log.d(TAG, "onTouchEvent: mXMove:"+mXMove+",mXLastMove:"+mXLastMove);
                Log.d(TAG, "onTouchEvent: mXMove:" + mXMove + ",scrolledX:" + scrolledX + ",getScrollX():" + getScrollX());
                if (getScrollX() + scrolledX < leftBorder) {
                    scrollTo(leftBorder, 0);
                    return true;
                } else if (getScrollX() + scrolledX > rightBorer-getWidth()) {
                    scrollTo(rightBorer - getWidth(), 0);
                    return true;
                }
                //x:view的左边缘-view内容的左边缘
                scrollBy(scrolledX, 0);//这个时候，view内容才开始滑动，之前都是手指的滑动
                Log.d(TAG, "onTouchEvent: getScrollX():"+getScrollX());
                mXLastMove = mXMove;
                Log.d(TAG, "onTouchEvent: mXLastMove:" + mXLastMove);
                break;
            case MotionEvent.ACTION_UP:
                //手指抬起时，根据当前的滚动值来判定应该滚动到哪一个子控件的界面
//                没有过半，就返回上一个子控件，过半了，就停在当前view
                int targeIndex = (getScrollX() + getWidth() / 2) / getWidth();//0开始
                int dx = targeIndex * getWidth() - getScrollX();//-1
                //第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                scroller.startScroll(getScrollX(), 0, dx, 0);//正数意味着向左滑动
                invalidate();
                Log.d(TAG, "onTouchEvent: targeIndex:" + targeIndex + ",(getScrollX() + getWidth() / 2):" + (getScrollX() + getWidth() / 2)
                        + ",getWidth():" + getWidth());
                Log.d(TAG, "onTouchEvent: dx:" + dx);
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        //第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            Log.d(TAG, "computeScroll: scroller.computeScrollOffset()：" + scroller.computeScrollOffset()
                    + ",scroller.getCurrX():" + scroller.getCurrX() + ",scroller.getCurrY():" + scroller.getCurrY());
            invalidate();
        }
    }
}

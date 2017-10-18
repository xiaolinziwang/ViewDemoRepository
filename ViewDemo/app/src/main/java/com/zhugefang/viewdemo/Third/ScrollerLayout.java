package com.zhugefang.viewdemo.Third;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by Administrator on 2017/10/18.
 */

public class ScrollerLayout extends ViewGroup {


    private final Scroller scroller;
    private final int touchSlop;
    private int leftBorder;
    private int rightBorer;

    public ScrollerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
        touchSlop = ViewConfiguration.get(context).getScaledDoubleTapSlop();
    }

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
                childView.layout(i * childView.getMeasuredWidth(), 0,
                        (i + 1) * childView.getMeasuredWidth(), childView.getMeasuredHeight());
            }
            leftBorder = getChildAt(0).getLeft();
            rightBorer = getChildAt(childCount - 1).getRight();
        }
    }
}

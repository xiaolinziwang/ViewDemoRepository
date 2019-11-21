package com.example.zidingyikongjiankaifa.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2019-11-21 18:22
 * 修改人：chunlinwang
 * 修改时间：2019-11-21 18:22
 * 修改备注：2个矩形是否相交
 */
public class RectViewIntersect extends View {

    private Paint mPaint;
    private RectF mRect1, mRect2, mRect3;


    public RectViewIntersect(Context context) {
        super(context);
        init();
    }


    public RectViewIntersect(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public RectViewIntersect(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mRect1 = new RectF(10, 10, 200, 200);
        mRect2 = new RectF(190, 10, 250, 200);
        mRect3 = new RectF(10, 210, 200, 300);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.RED);
        canvas.drawRect(mRect1, mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(mRect2, mPaint);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(mRect3, mPaint);
        //case1: 类的静态方法调用
        //boolean inter_1_2 = RectF.intersects(mRect1, mRect2);
        //case2: 类的成员方法调用
        //boolean inter_1_2 = mRect1.intersect(mRect2);
        //boolean inter_1_3 = RectF.intersects(mRect1, mRect3);
        //case3 很明显，intersects()函数只是判断是否相交，并不会改变原矩形 rect_1 的值。
        // 当 intersect() 函数判断的两个矩形不相交时，
        // 也不会改变 rect_1 的值;只有当两个矩形相交时，intersect() 函数才会把相交的点赋给 rect_1。而不是把4个数都是rect_1
        Log.d("RectViewIntersect", "onDraw: --rect--" + mRect1.toString());
        boolean result_1 = mRect1.intersects(190, 10, 250, 200);
        printResult(mRect1, result_1, "intersects");
        boolean result_2 = mRect1.intersect(210, 10, 250, 200);
        printResult(mRect1, result_2, "intersect");
        boolean result_3 = mRect1.intersect(191, 11, 251, 201);
        printResult(mRect1, result_3, "intersect");
        //case4
    }


    private void printResult(RectF rect, boolean result_1, String intersects) {
        Log.d("RectViewIntersect",
                "onDraw:" + "--" + intersects + "--rect--" + rect.toString() +
                        "---result---" + result_1);
    }
}

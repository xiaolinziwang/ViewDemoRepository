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
 * 类描述：合并
 * 创建人：chunlinwang
 * 创建时间：2019-11-21 19:20
 * 修改人：chunlinwang
 * 修改时间：2019-11-21 19:20
 * 修改备注：
 * case1：合并两个矩形的意思就是将两个矩形合并成一个矩形，即无论这两个矩形是否相交，
 * 取 两个矩形最小左上角点作为结果矩形的左上角点，取两个矩形最大右下角点作为结果矩形的 右下角点。
 * 如果要合并的两个矩形有一方为空，则将有值的一方作为最终结果
 * case2：先判断当前矩形与目标合并点的关系，如果不相交，则根据目标点(x,y)的位置，将目标
 * 点设置为当前矩形的左上角点或者右下角点。如果当前矩形是一个空矩形，则最后的结果矩
 * 形为([0,0],[x,y])，即结果矩形的左上角点为[0,0]，右下角点为[x,y]。
 */
public class RectViewUnion extends View {
    private Paint mPaint;
    private RectF mRect11, mRect1;


    public RectViewUnion(Context context) {
        super(context);
        init();
    }


    public RectViewUnion(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public RectViewUnion(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mRect1 = new RectF(10, 10, 50, 50);
        mRect11 = new RectF(10, 10, 50, 50);
        //mRect2 = new RectF(100, 100, 150, 160);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //case1:合并2个矩形
        //mPaint.setColor(Color.RED);
        //canvas.drawRect(mRect1, mPaint);
        //mPaint.setColor(Color.GREEN);
        //canvas.drawRect(mRect2, mPaint);
        //mPaint.setColor(Color.YELLOW);
        //mRect1.union(mRect2);
        //canvas.drawRect(mRect1, mPaint);
        //    case2：合并矩形与某个点
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(mRect11, mPaint);
        mRect1.union(100, 100);
        mPaint.setColor(Color.RED);
        canvas.drawRect(mRect1,mPaint);
        Log.d("RectViewUnion", "onDraw:-- "+mRect1.toString());
    }
}

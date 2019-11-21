package com.example.zidingyikongjiankaifa.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 项目名称：ViewDemo
 * 类描述：Rect、 RoundRect
 * 创建人：chunlinwang
 * 创建时间：2019-11-21 15:43
 * 修改人：chunlinwang
 * 修改时间：2019-11-21 15:43
 * 修改备注：
 */
public class RectView extends View {

    private Paint mPaint;
    private RectF mRect;
    private float mX;
    private float mY;


    public RectView(Context context) {
        super(context);
        init();
    }


    public RectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public RectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    /**
     * 备注人：chunlinwang
     * 备注时间：2019-11-21 17:19
     * 备注： case5
     * 判断是否包含某个点
     * 该函数用于判断某个点是否在当前矩形中。
     * 如果在，则返回 true;如果不在，则返回 false。 参数(x,y)就是当前要判断的点的坐标
     */
    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(5);
        mRect = new RectF(100, 10, 300, 100);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mX = event.getX();
        mY = event.getY();
        Log.d("onTouchEvent", "onTouchEvent:--mX== " + mX + "---mY==" + mY);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            invalidate();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            mX = -1;
            mY = -1;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //case1:圆角矩形
        //Paint paint = new Paint();
        //paint.setColor(Color.RED);
        //paint.setStyle(Paint.Style.FILL_AND_STROKE);
        //paint.setStrokeWidth(5);
        //RectF rect = new RectF(100, 10, 300, 100);
        // RectF rect:要绘制的矩形。
        // float rx:生成圆角的椭圆的 X 轴半径。
        //  float ry:生成圆角的椭圆的 Y 轴半径。
        //canvas.drawRoundRect(rect, 50, 50, paint);

        //case2:圆  半径=StrokeWidth/2+剩下的空白
        //canvas.drawCircle(100,100,50,paint);

        //case3：椭圆是由矩形生成的；一个矩形，决定一个椭圆
        //canvas.drawRect(rect,paint);
        //paint.setColor(Color.GREEN);
        //canvas.drawOval(rect,paint);
        //case4 弧是椭圆的一部分，而椭圆是根据矩形来生成的，所以弧也是根据矩形来生成的。
        //带两边 连接圆心
        //canvas.drawArc(rect,0,90, true,paint);
        //不带两边 ，只有圆弧
        //canvas.drawArc(rect,0,90, false,paint);
        //canvas.drawArc(rect,0,90, false,paint);
        //case5  判断是否包含某个点
        //该函数用于判断某个点是否在当前矩形中。
        // 如果在，则返回 true;如果不在，则返回 false。 参数(x,y)就是当前要判断的点的坐标
        //if (mRect.contains(mX,mY)){
        //    mPaint.setColor(Color.RED);
        //}else {
        //    mPaint.setColor(Color.GREEN);
        //}
        //canvas.drawRect(mRect,mPaint);
        //case 6  判断两个矩形是否相交
    }
}

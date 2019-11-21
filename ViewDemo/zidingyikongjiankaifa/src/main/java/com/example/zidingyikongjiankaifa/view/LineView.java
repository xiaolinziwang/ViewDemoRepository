package com.example.zidingyikongjiankaifa.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2019-11-21 15:21
 * 修改人：chunlinwang
 * 修改时间：2019-11-21 15:21
 * 修改备注：
 */
public class LineView extends View {
    public LineView(Context context) {
        super(context);
    }


    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //case1:一条直线
        //Paint paint = new Paint();
        //paint.setColor(Color.RED);
        //paint.setStyle(Paint.Style.FILL);
        //paint.setStrokeWidth(10);
        //canvas.drawLine(100, 100, 200, 200, paint);
        //case2:多条直线
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);
        //这几个参数的含义与多条直线中的参数含义相同。
        //float[] pts:点的合集，与上面的直线一致，样式为{x1,y1,x2,y2,x3,y3,...}。
        // int offset:集合中跳过的数值个数。注意不是点的个数!一个点有两个数值。
        //这里不是形成连接线，而是每两个点形成一
        //条直线，pts 的组织方式为{x1,y1,x2,y2,x3,y3,...}
        float[] pts = { 10, 10, 100, 100, 200, 200, 400, 400 };
        //2条直线
        //canvas.drawLines(pts, paint);
        //表示从 pts 数组中索引为 2 的数字开始绘图，有 4 个数值参与绘图，也就是点(100,100) 和(200,200)，
        // 所以效果图就是这两个点的连线。
        //canvas.drawLines(pts, 2, 4, paint);
        //  case3:多个点
        //canvas.drawPoints(pts, paint);
        canvas.drawPoints(pts, 2, 4, paint);
    }
}

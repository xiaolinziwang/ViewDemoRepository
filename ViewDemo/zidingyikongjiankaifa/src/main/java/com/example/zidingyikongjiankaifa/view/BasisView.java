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
 * 创建时间：2019-11-20 13:03
 * 修改人：chunlinwang
 * 修改时间：2019-11-20 13:03
 * 修改备注：
 * 但我们在示例中创建 Paint对象和其他对象时都是在 onDraw()函数中实现的，其实这在现 实代码中是不被允许的 。
 * 因为当需要重绘时就会调用 onDraw()函数，所以在 onDraw()函数中 创建的变量会一直被重复创建，
 * 这样会引起频繁的程序 GC C回收内存)，进而引起程序卡顿。
 * 这里之所以这样做，是因为可以提高代码的可读性 。 大家一定要记住，在 onDraw()函数中不 能创建变量 !
 * 一般在自定义控件的构造函数中创建变量，即在初始化时一次性创建。
 */
public class BasisView extends View {
    public BasisView(Context context) {
        super(context);
    }


    public BasisView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public BasisView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //case1：
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        //paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        canvas.drawCircle(65,65,60,paint);
        //case2：
        //Paint paint = new Paint();
        //paint.setColor(0xFFFF0000);
        //paint.setStyle(Paint.Style.FILL);
        //paint.setStrokeWidth(50);
        //canvas.drawCircle(190,200,150,paint);
        //paint.setColor(0x7EFFFF00);
        //canvas.drawCircle(190,200,100,paint);
        //case3
        //canvas.drawColor(Color.parseColor("#ffff0000"));
        //canvas.drawARGB(190,12,30,255);
        //case4 画直线 2点决定一定直线  起点（startX,startY）,终点(stopX,stopY)
        //Paint paint = new Paint();
        //paint.setColor(Color.RED);
        //paint.setStyle(Paint.Style.FILL);
        ////决定直线的粗细
        //paint.setStrokeWidth(10);
        //canvas.drawLine(100, 0, 100, 300, paint);
        //case5 画圆
        //Paint paint = new Paint();
        //paint.setColor(Color.RED);
        //paint.setStrokeWidth(100);
        //canvas.drawPoint(100,100,paint);
        //case6 画矩形
        //Rect rect1 = new Rect();
        //rect1.set(10,10,10,10);
        //距离Y轴10 距离X轴10，距离Y轴1000，距离X轴100
        //Rect rect = new Rect(100, 10, 250, 150);
        //Paint paint1 = new Paint();
        //paint1.setColor(Color.GREEN);
        //paint1.setStyle(Paint.Style.STROKE);
        //paint1.setStrokeWidth(1);
        //canvas.drawRect(rect,paint1);
        //case7 直线路径 最终成品：直角三角形
        //    Paint paint = new Paint();
        //    paint.setColor(Color.RED);
        //    paint.setStyle(Paint.Style.STROKE);
        //    paint.setStrokeWidth(10);
        //
        //    Path path = new Path();
        //    //设定起点位置
        //    path.moveTo(10, 10);
        //    //第二条终点
        //    path.lineTo(10, 100);
        //    //第三条终点
        //    path.lineTo(300, 100);
        //    //闭环
        //    path.close();
        //    canvas.drawPath(path, paint);
        //    case8 弧度路径
        //Paint paint = new Paint();
        //paint.setColor(Color.RED);
        //
        //paint.setStyle(Paint.Style.STROKE);
        //paint.setStrokeWidth(1);
        //Path path = new Path();
        //path.moveTo(10, 10);
        //RectF rectF = new RectF(100, 10, 200, 100);
        //canvas.drawRect(rectF,paint);
        ////这是一个画弧线路径的方法，弧线是从椭圆上截取的一部分 。
        //paint.setColor(Color.GREEN);
        ////参数 boolean forceMoveTo 的含义是是否强制地将弧的起始点作为绘制起始位置
        //path.arcTo(rectF, 0, 90,false);
        ////path.addArc(rectF,0,90);
        //canvas.drawPath(path, paint);
    }
}

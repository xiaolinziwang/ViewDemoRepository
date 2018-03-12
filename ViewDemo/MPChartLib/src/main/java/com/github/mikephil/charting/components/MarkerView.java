package com.github.mikephil.charting.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.lang.ref.WeakReference;

/**
 * View that can be displayed when selecting values in the chart. Extend this class to provide
 * custom layouts for your
 * markers.
 *
 * @author Philipp Jahoda
 */
public abstract class MarkerView extends RelativeLayout implements IMarker {
    private MPPointF mOffset = new MPPointF();
    protected MPPointF mOffset2 = new MPPointF();
    private WeakReference<Chart> mWeakChart;
    protected MPPointF mpPointF = new MPPointF();

    /**
     * relativeTouchPointX, relativeTouchPointY 是 touch 的点相对于 markerView 的 left, top 的坐标
     */
    private float relativeTouchPointX = 0;
    private float relativeTouchPointY = 0;

    public float getRelativeTouchPointX() {
        return relativeTouchPointX;
    }

    public void setRelativeTouchPointX(float relativeTouchPointX) {
        this.relativeTouchPointX = relativeTouchPointX;
    }
    public void setChartView(Chart chart) {
        mWeakChart = new WeakReference<>(chart);
    }
    /*
 添加接口，解决MarkView显示问题
  */
    public MPPointF getOffsetRight() {
        return mOffset;
    }

    public float getRelativeTouchPointY() {
        return relativeTouchPointY;
    }

    public void setRelativeTouchPointY(float relativeTouchPointY) {
        this.relativeTouchPointY = relativeTouchPointY;
    }

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public MarkerView(Context context, int layoutResource) {
        super(context);
        setupLayoutResource(layoutResource);
    }


    /**
     * Sets the layout resource for a custom MarkerView.
     */
    private void setupLayoutResource(int layoutResource) {

        View inflated = LayoutInflater.from(getContext()).inflate(layoutResource, this);

        inflated.setLayoutParams(new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        inflated.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec
                .makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

        // measure(getWidth(), getHeight());
        inflated.layout(0, 0, inflated.getMeasuredWidth(), inflated.getMeasuredHeight());
    }



    /**
     * Draws the MarkerView on the given position on the screen with the given Canvas object.
     */
    public void draw(Canvas canvas, float posX, float posY) {

//        // take offsets into consideration
//        posx += getXOffset(posx);
//        posy += getYOffset(posy);
//
//        // translate to the correct position and draw
//        canvas.translate(posx, posy);
//        draw(canvas);
//        canvas.translate(-posx, -posy);
//        this.mPosx = posx;
//        this.mPosy = posy;

        MPPointF offset = getOffsetForDrawingAtPoint(posX, posY);

        int saveId = canvas.save();
        // translate to the correct position and draw
        canvas.translate(posX + offset.x, posY + offset.y);
        draw(canvas);
        canvas.restoreToCount(saveId);

        mpPointF.x = posX;
        mpPointF.y = posY;
    }


    /**
     * This method enables a specified custom MarkerView to update it's content everytime the
     * MarkerView is redrawn.
     *
     * @param e         The Entry the MarkerView belongs to. This can also be any subclass of Entry, like
     *                  BarEntry or
     *                  CandleEntry, simply cast it at runtime.
     * @param highlight the highlight object contains information about the highlighted value such
     *                  as it's dataset-index, the
     *                  selected range or stack-index (only stacked bar entries).
     */
    public abstract void refreshContent(Entry e, Highlight highlight);





    @Override
    public void markViewClick() {
    }

    public MPPointF getOffset() {
        return mOffset;
    }

    public MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {

        MPPointF offset = getOffset();
        mOffset2.x = offset.x;
        mOffset2.y = offset.y;

        Chart chart = getChartView();
//
//        float width = getWidth();
        float height = getHeight();
//        //添加当点解右边往又显示
//        if (chart != null && posX + width + mOffset2.x > chart.getWidth()) {
//            offset = getOffsetRight();
//            mOffset2.x = offset.x;
//        }
//        if (posX + mOffset2.x < 0) {
//            mOffset2.x = -posX;
//        } else if (chart != null && posX + width + mOffset2.x > chart.getWidth()) {
//            mOffset2.x = chart.getWidth() - posX - width;
//        }
        if(posX>getChartView().getRight()/2){
            //如果右边界超过chart右边界，设置MarkView固定于左上角
            mOffset2.x=-getWidth();
        }else if (posX<getChartView().getRight()/2){
            mOffset2.x=0f;
        }
        mOffset2.y=-posY+getChartView().getViewPortHandler().offsetTop();

//        if (posY + mOffset2.y < 0) {
//            mOffset2.y = -posY;
//        } else if (chart != null && posY + height + mOffset2.y > chart.getHeight()) {
//            mOffset2.y = chart.getHeight() - posY - height;
//        }

        return mOffset2;
    }

    public Chart getChartView() {
        return mWeakChart == null ? null : mWeakChart.get();
    }

}

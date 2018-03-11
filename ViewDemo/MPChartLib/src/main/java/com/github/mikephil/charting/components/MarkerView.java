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
    private MPPointF mOffset2 = new MPPointF();
    private WeakReference<Chart> mWeakChart;
    private MPPointF mpPointF = new MPPointF();

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


    float mPosx, mPosy;

    @Override
    public boolean isTouch(MotionEvent event) {
        if (mpPointF.x == 0)
            return false;

        float left = mpPointF.x - getWidth() / 2;
        float top = mpPointF.y - getHeight();
        float right = mpPointF.x + getWidth() / 2;
        float bottom = mpPointF.y;

        boolean touchInMarkerView = new RectF(left, top, right, bottom).contains(event.getX(), event.getY());

        if (touchInMarkerView) {
            setRelativeTouchPointX(event.getX() - left);
            setRelativeTouchPointY(event.getY() - top);
        }

        mpPointF.x = 0;
        mpPointF.y = 0;

        return touchInMarkerView;
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

    /**
     * Use this to return the desired offset you wish the MarkerView to have on the x-axis. By
     * returning -(getWidth() /
     * 2) you will center the MarkerView horizontally.
     *
     * @param xpos the position on the x-axis in pixels where the marker is drawn
     */


    /**
     * Use this to return the desired position offset you wish the MarkerView to have on the
     * y-axis.
     * By returning
     * -getHeight() you will cause the MarkerView to be above the selected value.
     *
     * @param ypos the position on the y-axis in pixels where the marker is drawn
     */
//    public abstract int getYOffset(float ypos);


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

        float width = getWidth();
        float height = getHeight();

        if (posX + mOffset2.x < 0) {
            mOffset2.x = -posX;
        } else if (chart != null && posX + width + mOffset2.x > chart.getWidth()) {
            mOffset2.x = chart.getWidth() - posX - width;
        }

        if (posY + mOffset2.y < 0) {
            mOffset2.y = -posY;
        } else if (chart != null && posY + height + mOffset2.y > chart.getHeight()) {
            mOffset2.y = chart.getHeight() - posY - height;
        }

        return mOffset2;
    }

    public Chart getChartView() {
        return mWeakChart == null ? null : mWeakChart.get();
    }

}

package com.zhugefang.viewdemo;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.github.mikephil.charting.charts.LineChart;

/**
 * Created by chunLin on 2018/3/11.
 */

public class MyChart extends LineChart {
    public MyChart(Context context) {
        super(context);
    }

    public MyChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean handled = true;
        // if there is no marker view or drawing marker is disabled
        if (isShowingMarker() && this.getMarkerView() instanceof ChartInfoMarkerView){
            ChartInfoMarkerView markerView = (ChartInfoMarkerView) this.getMarkerView();
            Rect rect = new Rect((int)markerView.drawingPosX,(int)markerView.drawingPosY,(int)markerView.drawingPosX + markerView.getWidth(), (int)markerView.drawingPosY + markerView.getHeight());
            if (rect.contains((int) event.getX(),(int) event.getY())) {
                // touch on marker -> dispatch touch event in to marker
                markerView.dispatchTouchEvent(event);
            }else{
                handled = super.onTouchEvent(event);
            }
        }else{
            handled = super.onTouchEvent(event);
        }
        return handled;
    }

    private boolean isShowingMarker(){
        return getMarkerView() != null && isDrawMarkerViewEnabled() && valuesToHighlight();
    }
}

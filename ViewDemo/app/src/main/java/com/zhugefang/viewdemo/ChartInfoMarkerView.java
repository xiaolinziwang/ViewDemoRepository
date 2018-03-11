package com.zhugefang.viewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chunLin on 2018/3/11.
 */

public class ChartInfoMarkerView extends MarkerView {
     @Bind(R.id.tvContent)
     TextView markerContainerView;

    protected float drawingPosX;
    protected float drawingPosY;
    private static final int MAX_CLICK_DURATION = 500;
    private long startClickTime;

    /**
     * The constructor
     *
     * @param context
     * @param layoutResource
     */

    public ChartInfoMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        ButterKnife.bind(this);
        markerContainerView.setClickable(true);
        markerContainerView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MARKER","click");
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                startClickTime = Calendar.getInstance().getTimeInMillis();
                break;
            }
            case MotionEvent.ACTION_UP: {
                long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                if(clickDuration < MAX_CLICK_DURATION) {
                    markerContainerView.performClick();
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(Canvas canvas, float posX, float posY) {
        super.draw(canvas, posX, posY);
        MPPointF offset = getOffsetForDrawingAtPoint(posX, posY);
        this.drawingPosX = posX + offset.x;
        this.drawingPosY = posY + offset.y;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {

    }
}
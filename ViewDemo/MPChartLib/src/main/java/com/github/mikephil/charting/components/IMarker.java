package com.github.mikephil.charting.components;

import android.view.MotionEvent;

public interface IMarker {
    boolean isTouch(MotionEvent event);

    void markViewClick();
}

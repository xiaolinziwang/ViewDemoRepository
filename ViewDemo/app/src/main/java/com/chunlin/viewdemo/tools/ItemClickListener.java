package com.chunlin.viewdemo.tools;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/9/24.
 */
public class ItemClickListener extends RecyclerView.SimpleOnItemTouchListener {

    private OnItemClickListener clickListener;
    private GestureDetectorCompat gestureDetector;

    public ItemClickListener(final RecyclerView recyclerView,
                             OnItemClickListener listener) {
        this.clickListener = listener;
        gestureDetector = new GestureDetectorCompat(recyclerView.getContext(),
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        if (childView != null && clickListener != null) {
                            int childAdapterPosition = recyclerView.getChildAdapterPosition(childView);
                            if (childAdapterPosition != RecyclerView.NO_POSITION) {
                                clickListener.onItemClick(childView, childAdapterPosition);
                            }
                        }
                        return true;
                    }


                    @Override
                    public void onLongPress(MotionEvent e) {
                        View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        if (childView != null && clickListener != null) {
                            int childAdapterPosition = recyclerView.getChildAdapterPosition(childView);
                            if (childAdapterPosition != RecyclerView.NO_POSITION) {
                                clickListener.onItemLongClick(childView,
                                        childAdapterPosition);
                            }
                        }
                    }
                });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetector.onTouchEvent(e);
        return false;
    }

    public interface OnItemClickListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

}

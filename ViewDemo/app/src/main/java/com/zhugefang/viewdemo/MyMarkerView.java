package com.zhugefang.viewdemo;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.zhugefang.viewdemo.tools.PxAndDp;

/**
 * Created by Administrator on 2016/5/6.
 * 折线图点击折点显示信息
 */
public class MyMarkerView extends MarkerView {
    private final TextView tv_all;
    public TextView tvContent;
    private int containerWidth;
    private int containerHeight;

    private CallBack callBack;
    private int offset = 10;
    private String unit;

    private LineData lineData;
    private int btnLeft;
    private int btnTop;
    private int btnRight;
    private int btnBottom;
    Context mContext;


    public MyMarkerView(final Context context, int layoutResource) {
        super(context, layoutResource);
        this.mContext = context;
        tvContent = (TextView) findViewById(R.id.tvContent);
        tv_all = (TextView) findViewById(R.id.tv_all);
        offset = PxAndDp.dip2px(context, 5);
    }


    public void setLineData(LineData lineData) {
        this.lineData = lineData;
    }


    public void setUnit(String unit) {
        this.unit = unit;
    }


    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }


    public void setContainerSize(int containerWidth, int containerHeight) {
        this.containerWidth = containerWidth;
        this.containerHeight = containerHeight;
    }


    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        if (e instanceof CandleEntry) {

            CandleEntry ce = (CandleEntry) e;

            tvContent.setText("" + Utils.formatNumber(ce.getHigh(), 0, true));
        } else {
            // TODO: 2016/6/18
            int index = highlight.getXIndex();
            if (lineData != null) {
                int count = lineData.getDataSetCount();

                StringBuilder builder = new StringBuilder();
                String str = lineData.getXVals().get(index);
                tvContent.setText(str);
                builder.append(str);
                for (int i = 0; i < count; i++) {
                    ILineDataSet data = lineData.getDataSetByIndex(i);
                    int entryCount = data.getEntryCount();
                    for (int x = 0; x < entryCount; x++) {
                        Entry entry = data.getEntryForIndex(x);
                        if (entry.getXIndex() == index) {
                            if (TextUtils.isEmpty(unit)) {
                                builder.append(
                                        "\n" + data.getLabel() + ":" + (int) entry.getVal() + "");
                            } else {
                                builder.append(
                                        "\n" + data.getLabel() + ":" + (int) entry.getVal() + unit);
                            }
                            break;
                        }
                    }
                }
                SpannableString spannableString = new SpannableString(builder.toString());
                spannableString
                        .setSpan(new AbsoluteSizeSpan((int) (tvContent.getTextSize() * 1.2)), 0, str
                                .length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvContent.setText(spannableString);
                return;
            }
            //如果callback不为空 就不需要下面那段逻辑
            if (callBack != null) {
                tvContent.setText(callBack.getInfo(index));
            }
        }
    }


    @Override
    public int getXOffset(float xpos) {
        // this will center the marker-view horizontally
        if (xpos + getWidth() + offset > this.containerWidth) {
            return -getWidth() - offset;
        }
        return offset;
    }


    @Override
    public int getYOffset(float ypos) {
        // this will cause the marker-view to be above the selected value
        return -getHeight();
        //        if (ypos - getHeight() - offset < 0) {
        //            if (ypos == getHeight()) { //详情页价格分析折线图使用这个条件  ypos有问题
        //                return (int) -ypos / 2;
        //            }
        //            return -offset;
        //        }
        //        return (-getHeight() - offset);
    }

    //    @Override
    //    public int getYOffset(float ypos) {
    //        // this will cause the marker-view to be above the selected value
    //        LogUtils.d("aaa", "ypos:" + ypos + ",getHeight() :" + getHeight() + "," + containerHeight);
    //        if (ypos - getHeight() - offset < 0) {
    //            LogUtils.d("aaa", "-------y-----");
    //            if (ypos == getHeight()) { //详情页价格分析折线图使用这个条件  ypos有问题
    //                return (int) -ypos / 2;
    //            }
    //            return -offset;
    //        }
    //        return (-getHeight() - offset);
    //    }

    public interface CallBack {
        CharSequence getInfo(int index);
    }


    @Override
    public void markViewClick() {
        Toast.makeText(mContext, "onClick 全部!", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean isTouch(MotionEvent event) {
        btnLeft = tv_all.getLeft();
        btnTop = tv_all.getTop();
        btnRight = tv_all.getRight();
        btnBottom = tv_all.getBottom();
        int width = getWidth();
        int height = getHeight();
        Log.d("MyMarkerView",
                "tv_all: " + btnLeft + "、" + btnTop + "、" + btnRight + "、" + btnBottom);
        Log.d("MyMarkerView", "tv_all: getX():" + tv_all.getX() + "、getY():" + tv_all.getY());
        Log.d("MyMarkerView",
                "marker_view: " + getLeft() + "、" + getTop() + "、" + getRight() + "、" +
                        getBottom());
        Log.d("MyMarkerView", "marker_view: " + getX() + "、" + getY());
        Log.d("MyMarkerView", "event.getX(): " + event.getX() + ",event.getY():" + event.getY());
        return event.getY() <= getBottom()&&tv_all.getY()<event.getY();
        //if (mPosy == 0) {
        //    return false;
        //}
        //float left = mPosx - getWidth() / 2;
        //float top = mPosy - getHeight();
        //float right = mPosx + getWidth() / 2;
        //float bottom = mPosy;
        //
        //boolean touchInMarkerView = new RectF(left, top, right, bottom)
        //        .contains(event.getX(), event.getY());
        //
        //if (touchInMarkerView) {
        //    setRelativeTouchPointX(event.getX() - left);
        //    setRelativeTouchPointY(event.getY() - top);
        //}
        //
        //mPosx = 0;
        //mPosy = 0;

        //return touchInMarkerView;

    }
}

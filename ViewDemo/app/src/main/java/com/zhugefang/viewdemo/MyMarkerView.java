package com.zhugefang.viewdemo;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.zhugefang.viewdemo.Twelve.TimeMachineAdapter;
import com.zhugefang.viewdemo.tools.ItemClickListener;
import com.zhugefang.viewdemo.tools.PxAndDp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/6.
 * 折线图点击折点显示信息
 */
public class MyMarkerView extends MarkerView {

    private final RecyclerView rv;
    private final TimeMachineAdapter mTimeMachineAdapter;
    public TextView tvContent;
    private int containerWidth;
    private int containerHeight;

    private CallBack callBack;
    private int offset = 10;
    private String unit;

    private LineData lineData;


    public MyMarkerView(final Context context, int layoutResource) {
        super(context, layoutResource);

        tvContent = (TextView) findViewById(R.id.tvContent);
        rv = (RecyclerView) findViewById(R.id.rv);
        tvContent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"textview",Toast.LENGTH_LONG).show();
            }
        });
        offset = PxAndDp.dip2px(context, 5);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(linearLayoutManager);
        mTimeMachineAdapter = new TimeMachineAdapter(context, dataList);
        rv.setAdapter(mTimeMachineAdapter);
        rv.addOnItemTouchListener(new ItemClickListener(rv, new ItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(context,"aaa",Toast.LENGTH_LONG).show();
            }


            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(context,"BBBBB",Toast.LENGTH_LONG).show();
            }
        }));
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


    private List<String> dataList = new ArrayList<>();


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
                                dataList.add(data.getLabel() + ":" + (int) entry.getVal() + "");
                            } else {
                                builder.append(
                                        "\n" + data.getLabel() + ":" + (int) entry.getVal() + unit);
                                dataList.add(data.getLabel() + ":" + (int) entry.getVal() + "");
                            }
                            break;
                        }
                    }
                }
                mTimeMachineAdapter.notifyDataSetChanged();
                //SpannableString spannableString = new SpannableString(builder.toString());
                //spannableString
                //        .setSpan(new AbsoluteSizeSpan((int) (tvContent.getTextSize() * 1.2)), 0, str
                //                .length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                //tvContent.setText(spannableString);
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
}

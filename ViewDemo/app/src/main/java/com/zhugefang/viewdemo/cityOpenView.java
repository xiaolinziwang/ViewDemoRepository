package com.zhugefang.viewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2018/2/1 18:17
 * 修改人：Administrator
 * 修改时间：2018/2/1 18:17
 * 修改备注：
 */
public class cityOpenView extends RelativeLayout {
    Context context;
    int width;


    public cityOpenView(Context context) {
        this(context, null);
        this.context = context;
    }


    public void setWidth(int width) {
        this.width = width;
    }


    public cityOpenView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View inflate = LayoutInflater.from(context).inflate(R.layout.tv_main, this, true);
    }


    public cityOpenView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }
}

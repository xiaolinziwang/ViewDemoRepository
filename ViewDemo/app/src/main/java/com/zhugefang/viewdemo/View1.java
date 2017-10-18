package com.zhugefang.viewdemo;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/9/27.
 */

public class View1 extends View {
    private String TAG = "View1";
    private Context mContex;

    public View1(Context context) {
        super(context);
    }

    public View1(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContex = context;
    }

    public View1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Configuration configuration = mContex.getResources().getConfiguration();
        int screenWidthDp = configuration.screenWidthDp;
        int screenHeightDp = configuration.screenHeightDp;


//        widthMeasureSpec:1111111111111111111101111001000---2147482568
        int widthMeasureSize = MeasureSpec.getSize(widthMeasureSpec);//10000111000==1080
        int widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);//10000000000000000000000000000000=2147483648
//heightMeasureSpec  1111111111111111110010101000111==2147481937
        int heightMeasureSize = MeasureSpec.getSize(heightMeasureSpec);//11010101111=1711
        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);//2147483648--10000000000000000000000000000000
        Log.d(TAG, "onMeasure: widthMeasureSpec:" + widthMeasureSpec);
        Log.d(TAG, "widthMeasureSize:" + widthMeasureSize + "--widthMeasureMode:" + widthMeasureMode);

        Log.d(TAG, "onMeasure: heightMeasureSpec:" + heightMeasureSpec);
        Log.d(TAG, "heightMeasureSize:" + heightMeasureSize + "--heightMeasureMode:" + heightMeasureMode);
        Log.d(TAG, "widthMeasureSpec: " + MeasureSpec.makeMeasureSpec(widthMeasureSize, widthMeasureMode));
        Log.d(TAG, "heightMeasureSpec: " + MeasureSpec.makeMeasureSpec(heightMeasureSize, heightMeasureMode));
        Log.d(TAG, "onMeasure: screenWidthDp:"+screenWidthDp+"转成px:"+dip2px(mContex,screenWidthDp));
        Log.d(TAG, "onMeasure: screenHeightDp:"+screenHeightDp+"转成px:"+dip2px(mContex,screenHeightDp));
    }
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}

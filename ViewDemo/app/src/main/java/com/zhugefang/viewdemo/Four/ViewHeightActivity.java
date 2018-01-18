package com.zhugefang.viewdemo.Four;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;

import com.zhugefang.viewdemo.R;

public class ViewHeightActivity extends AppCompatActivity {

    private Button mbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_height);
        mbtn = ((Button) findViewById(R.id.btn));
    }

    /*
    * 获取view宽高:方法1   被多次调用
    * 1、Activity/View#onWindowFocusChanged():得到或者失去焦点会被调用，即onResume和onPause被触发时
    * */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            int measuredHeight = mbtn.getMeasuredHeight();
            int measuredWidth = mbtn.getMeasuredWidth();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
//        方法2:投递到消息队列的尾部，等待Looper调用Runnable的时候，view已经初始化好了  被多次调用
//        mbtn.post(new Runnable() {
//            @Override
//            public void run() {
//                int measuredHeight = mbtn.getMeasuredHeight();
//                int measuredWidth = mbtn.getMeasuredWidth();
//            }
//        });
        //方法3：当view树的状态发生改变或者view树内部的view的可见性发生改变时，onGlobalLayout会被回调  被多次调用
        ViewTreeObserver observer = mbtn.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                mbtn.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int measuredHeight = mbtn.getMeasuredHeight();
                int measuredWidth = mbtn.getMeasuredWidth();
            }
        });
    }

    public void manualMeasure() {
        //需要根据View的LayoutParms来分：
//        Match_parent
//        手动赋值px
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(100, View.MeasureSpec.EXACTLY);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(100, View.MeasureSpec.EXACTLY);
        mbtn.measure(widthMeasureSpec, heightMeasureSpec);
//Wrap_content:使用最多尺寸
        int widthMeasureSpec1 = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec1 = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.AT_MOST);
        mbtn.measure(widthMeasureSpec1, heightMeasureSpec1);
//        错误1：
//        int heightMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(-1, View.MeasureSpec.UNSPECIFIED);
        //错误2
        mbtn.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

}

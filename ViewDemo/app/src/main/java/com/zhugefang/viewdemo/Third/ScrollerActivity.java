package com.zhugefang.viewdemo.Third;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zhugefang.viewdemo.R;

/**
 * scrollTo(x,y)\scrollBy(x,y)只能移动内容，x,y是距离，正向是向左，负的方向是向右，
 * （起始位置-末位置），不是坐标，
 * 滚动效果是跳跃式的
 * http://blog.csdn.net/guolin_blog/article/details/48719871
 * 步骤：
 * 1. 创建Scroller的实例
 2. 调用startScroll()方法来初始化滚动数据并刷新界面
 3. 重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
 */
public class ScrollerActivity extends BaseActivity {

    private LinearLayout layout;
    private Button scrollToBtn;
    private Button scrollByBtn;
    private float dp_60;
    private float dp_100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = (LinearLayout) findViewById(R.id.layout);
        scrollToBtn = (Button) findViewById(R.id.scroll_to_btn);
        scrollByBtn = (Button) findViewById(R.id.scroll_by_btn);
        dp_60 = getResources().getDimension(R.dimen.dp_60);
        dp_100 = getResources().getDimension(R.dimen.dp_100);
        scrollToBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.scrollTo(-((int) dp_60), -((int) dp_100));
            }
        });
        scrollByBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.scrollBy(-((int) dp_60), -((int) dp_100));
            }
        });
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_scroller;
    }
}

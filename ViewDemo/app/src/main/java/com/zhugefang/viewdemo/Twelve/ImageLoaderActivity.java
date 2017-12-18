package com.zhugefang.viewdemo.Twelve;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;

import com.zhugefang.viewdemo.R;

public class ImageLoaderActivity extends AppCompatActivity {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader);
        gridView = ((GridView) findViewById(R.id.gridView1));
        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState==AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                    //可以加载数据
                }else {
//                    不可以加载数据
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }
}

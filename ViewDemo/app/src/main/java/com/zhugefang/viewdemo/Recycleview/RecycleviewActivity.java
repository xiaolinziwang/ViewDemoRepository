package com.zhugefang.viewdemo.Recycleview;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.zhugefang.viewdemo.R;
import com.zhugefang.viewdemo.Third.BaseActivity;
import java.util.ArrayList;
import java.util.List;

public class RecycleviewActivity extends BaseActivity {
    private String TAG = this.getClass().getSimpleName();
    @Bind(R.id.rv)
    RecyclerView rv;
    private List<String> dataList = new ArrayList<>();
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate: 0");
        initData();
        Log.d(TAG, "onCreate: 2");
        initAdapter();
        Log.d(TAG, "onCreate: 4");
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_recycleview;
    }


    private void initAdapter() {
//        LinearLayoutManager layout = new LinearLayoutManager(this);
//        LinearLayoutManager layout = new LinearLayoutManager(this);
//        layout.setOrientation(LinearLayoutManager.HORIZONTAL);
//        GridLayoutManager layout = new GridLayoutManager(this, 4);
//        GridLayoutManager layout = new GridLayoutManager(this, 4,GridLayoutManager.HORIZONTAL,true);
        StaggeredGridLayoutManager layout = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
//        StaggeredGridLayoutManager layout = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL);
        rv.setLayoutManager(layout);
        adapter = new MainAdapter(dataList, this);
        rv.setAdapter(adapter);
        Log.d(TAG, "onCreate: 3");
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            dataList.add("纵向和横向滑动");
            dataList.add("纵向和横向瀑布流");
            dataList.add("添加头布局和脚布局");
            dataList.add("下拉刷新和上拉加载");
            dataList.add("多布局页面");
            dataList.add("滑动删除");
            dataList.add("点击事件");
            dataList.add("添加空布局");
            dataList.add("添加分割线");
        }
        Log.d(TAG, "onCreate: 1");
    }


}

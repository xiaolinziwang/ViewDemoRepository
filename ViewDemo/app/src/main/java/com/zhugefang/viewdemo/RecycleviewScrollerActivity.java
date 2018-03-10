package com.zhugefang.viewdemo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.Bind;
import com.zhugefang.viewdemo.Third.BaseActivity;
import com.zhugefang.viewdemo.Twelve.TimeMachineAdapter;
import java.util.ArrayList;
import java.util.List;

public class RecycleviewScrollerActivity extends BaseActivity {
    @Bind(R.id.rv) RecyclerView rv;

    private List<String> dataList = new ArrayList<>();
    private TimeMachineAdapter mTimeMachineAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(linearLayoutManager);
        dataList.add("aaaaaaaa");
        dataList.add("bbbbb");
        dataList.add("cccccccc");
        dataList.add("ddddd");
        dataList.add("ffffff");
        dataList.add("eeee");
        dataList.add("gggggg");
        dataList.add("kkkkkkk");
        mTimeMachineAdapter = new TimeMachineAdapter(this, dataList);
        rv.setAdapter(mTimeMachineAdapter);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_recycleview_scroller;
    }
}

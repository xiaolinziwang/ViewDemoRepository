package com.example.desiginbehavior;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    List<String> dataList = new ArrayList<>();
    private CollapsingToolbarLayout mcollaps;
    private AppBarLayout mAppbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = ((RecyclerView) findViewById(R.id.rv));
        mcollaps = ((CollapsingToolbarLayout) findViewById(R.id.collaps));
        mAppbar = ((AppBarLayout) findViewById(R.id.appbar));
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAppbar.setExpanded(false);
                mRecyclerView.scrollToPosition(0);
            }
        });
        findViewById(R.id.open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAppbar.setExpanded(true);
                mRecyclerView.scrollToPosition(0);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        for (int i = 0; i < 60; i++) {
            dataList.add("item--------" + i);
        }
        StringAdapter stringAdapter = new StringAdapter(this, dataList);
        mRecyclerView.setAdapter(stringAdapter);
    }
}

package com.zhugefang.viewdemo.Recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhugefang.viewdemo.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/12/7.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    private final LayoutInflater inflater;
    private List<String> mList;

    public MainAdapter(List<String> mList, Context mContext) {
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recycleview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv)
        TextView tv;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

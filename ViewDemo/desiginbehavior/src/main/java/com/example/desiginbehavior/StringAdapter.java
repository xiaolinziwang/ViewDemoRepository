package com.example.desiginbehavior;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2018/6/5 上午11:45
 * 修改人：chunlinwang
 * 修改时间：2018/6/5 上午11:45
 * 修改备注：
 */
public class StringAdapter extends RecyclerView.Adapter {
    private final LayoutInflater mInflater;
    private Context mContext;
    private List<String> dataList;


    public StringAdapter(Context context, List<String> dataList) {
        mContext = context;
        this.dataList = dataList;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mInflater.inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        holder1.mTv.setText(dataList.get(position));
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
      TextView mTv;


        MyViewHolder(View view) {
            super(view);
            mTv=((TextView) view.findViewById(R.id.tv));
        }
    }
}

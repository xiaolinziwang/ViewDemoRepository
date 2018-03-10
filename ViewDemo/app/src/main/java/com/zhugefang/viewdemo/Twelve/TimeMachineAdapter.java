package com.zhugefang.viewdemo.Twelve;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.zhugefang.viewdemo.R;
import java.util.List;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2018/3/9 14:42
 * 修改人：Administrator
 * 修改时间：2018/3/9 14:42
 * 修改备注：
 */
public class TimeMachineAdapter extends RecyclerView.Adapter {
    private final LayoutInflater mInflater;
    Context mContext;
    List<String> data;


    public TimeMachineAdapter(Context context, List<String> data) {
        mContext = context;
        this.data = data;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mInflater.inflate(R.layout.time_machine_marker_view, parent, false);
        return new ViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.mTvContent.setText(data.get(position));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tvContent) TextView mTvContent;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

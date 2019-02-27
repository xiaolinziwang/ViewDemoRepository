package com.chunlin.viewdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import butterknife.ButterKnife;
import java.util.List;

/**
 * Created by chunlin on 2016/9/18.
 */
public class BasicInfoAdapter extends BaseAdapter {
    public boolean isDisplay;
    private Context context;
    private int type;
    List<String> dataList;


    public BasicInfoAdapter(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @Override
    public int getCount() {
        return dataList.size();
    }


    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.tv_main, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = ((ViewHolder) convertView.getTag());
        }

        return convertView;
    }


    static class ViewHolder {
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

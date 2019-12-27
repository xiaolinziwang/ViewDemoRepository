package com.example.sdk;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2019-12-27 21:18
 * 修改人：chunlinwang
 * 修改时间：2019-12-27 21:18
 * 修改备注：
 */

import android.view.View;

/**
 * Created by 王灼洲 on 2018/7/22
 */
/*public*/ class WrapperOnClickListener implements View.OnClickListener {
    private View.OnClickListener source;

    WrapperOnClickListener(View.OnClickListener source) {
        this.source = source;
    }

    @Override
    public void onClick(View view) {
        //调用原有的 OnClickListener
        try {
            if (source != null) {
                source.onClick(view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //插入埋点代码
        SensorsDataPrivate.trackViewOnClick(view);
    }
}


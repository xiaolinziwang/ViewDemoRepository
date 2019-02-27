package com.chunlin.viewdemo.Eleven;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.Bind;
import com.chunlin.viewdemo.R;
import com.chunlin.viewdemo.Third.BaseActivity;

public class MyIntentServiceActivity extends BaseActivity {
    public static final String ACTION_TYPE_SERVICE = "service";
    public static final String ACTION_TYPE_THREAD = "thread";
    @Bind(R.id.tv) TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case ACTION_TYPE_SERVICE:
                    tv.setText("服务状态："+intent.getStringExtra("status"));
                    break;
            }
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_my_intent_service;
    }
}

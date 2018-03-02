package com.zhugefang.viewdemo.Eleven;

import android.app.IntentService;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zhugefang.viewdemo.R;
import com.zhugefang.viewdemo.Third.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class LocalIntentActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_local_intent;
    }

    @OnClick({R.id.btn_first, R.id.btn_second})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_first:
                Toast.makeText(this, "aaa", Toast.LENGTH_LONG).show();
                startLocalService(LocalIntentService.class);
                break;
            case R.id.btn_second:
                startLocalService(LocalIntentService1.class);
                break;
        }
    }

    private void startLocalService(Class localClass) {
        Intent service = new Intent(this, localClass);
        service.putExtra("task_action", "com.ryg.action.TASK1");
        startService(service);
        service.putExtra("task_action", "com.ryg.action.TASK2");
        startService(service);
        service.putExtra("task_action", "com.ryg.action.TASK3");
        startService(service);
    }
}

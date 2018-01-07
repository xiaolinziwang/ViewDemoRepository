package com.zhugefang.viewdemo.Ten;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.zhugefang.viewdemo.R;

public class HandlerActivity extends AppCompatActivity {

    private TextView tv_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        tv_button = ((TextView) findViewById(R.id.tv_button));

//        Handler handler = new Handler();
//        handler.sendMessage()
        final ThreadLocal<Boolean> mBooleanThreadLocal = new ThreadLocal<>();
        mBooleanThreadLocal.set(true);
        mBooleanThreadLocal.set(false);

    }

}

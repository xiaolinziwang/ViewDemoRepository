package com.zhugefang.viewdemo.First;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zhugefang.viewdemo.R;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_third;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btn_third = ((Button) findViewById(R.id.btn));
        btn_third.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                startActivity(new Intent(this, LoadModeActivity.class));
                break;
        }
    }
}

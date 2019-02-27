package com.chunlin.viewdemo.First;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.chunlin.viewdemo.R;

public class LoadModeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_sec;
    private Button btn_third;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn_sec = ((Button) findViewById(R.id.btn_sec));
        btn_sec.setVisibility(View.GONE);
        btn_third = ((Button) findViewById(R.id.btn_third));

        btn_sec.setOnClickListener(this);
        btn_third.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sec:
                startActivity(new Intent(this, SecondActivity.class));
                break;
            case R.id.btn_third:
                startActivity(new Intent(this, ThirdActivity.class));
                break;
        }
    }
}

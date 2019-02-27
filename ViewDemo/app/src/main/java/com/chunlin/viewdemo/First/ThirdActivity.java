package com.chunlin.viewdemo.First;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.chunlin.viewdemo.R;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        btn_main = ((Button) findViewById(R.id.btn_main));
        btn_main.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main:
                startActivity(new Intent(this, SecondActivity.class));
                break;
        }
    }
}

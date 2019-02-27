package com.chunlin.viewdemo.First.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chunlin.viewdemo.R;

public class StartActivity extends AppCompatActivity {
    private String TAG = "StartActivity";
    private TextView tv_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        tv_start = ((TextView) findViewById(R.id.tv_start));
        tv_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, Start2Activity.class));
            }
        });
        Log.d(TAG, "onCreate:StartActivity ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: StartActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: StartActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: StartActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: StartActivity");
    }
}


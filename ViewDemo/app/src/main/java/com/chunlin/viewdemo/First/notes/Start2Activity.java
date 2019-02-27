package com.chunlin.viewdemo.First.notes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.chunlin.viewdemo.R;

public class Start2Activity extends AppCompatActivity {
    private String TAG = "StartActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start2);
        Log.d(TAG, "onCreate: Start2Activity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart:Start2Activity ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: Start2Activity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: Start2Activity");
    }
}

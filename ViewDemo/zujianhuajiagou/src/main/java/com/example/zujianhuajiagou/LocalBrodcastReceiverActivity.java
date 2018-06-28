package com.example.zujianhuajiagou;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class LocalBrodcastReceiverActivity extends AppCompatActivity {
private String TAG="LocalBrodcastReceiverActivity";
    private LocalBroadcastManager mManager;
    private BroadcastReceiver mReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_brodcast_receiver);
        mManager = LocalBroadcastManager.getInstance(this);
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG, "onReceive: " + "I LOVE Wangchunlin");
            }
        };
        mManager.registerReceiver(mReceiver,new IntentFilter("dongminglei"));
    }


    @Override
    protected void onResume() {
        super.onResume();
        mManager.sendBroadcast(new Intent("dongminglei"));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mManager.unregisterReceiver(mReceiver);
    }
}

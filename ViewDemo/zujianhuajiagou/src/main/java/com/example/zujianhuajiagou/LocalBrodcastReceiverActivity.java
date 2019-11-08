package com.example.zujianhuajiagou;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

public class LocalBrodcastReceiverActivity extends AppCompatActivity {
    private String TAG = "LocalBrodcastReceiverActivity";
    private LocalBroadcastManager mManager;
    private LocalBroadcastReceiver mReceiver;
    private LocalBroadcastReceiver mReceiver1;


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LocalBrodcastReceiverActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_brodcast_receiver);
        mManager = LocalBroadcastManager.getInstance(this);
        mReceiver = new LocalBroadcastReceiver();
        mReceiver1 = new LocalBroadcastReceiver();
        mManager.registerReceiver(mReceiver, new IntentFilter("dongminglei"));
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

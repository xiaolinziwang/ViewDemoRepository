package com.chunlin.viewdemo.Eleven;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by chunLin on 2018/3/2.
 */

public class LocalIntentService1 extends IntentService {
    public static final String TAG = "LocalIntentService1";

    public LocalIntentService1() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getStringExtra("task_action");
        Log.d(TAG, "receive task: " + action);
        SystemClock.sleep(3000);
        if ("com.ryg.action.TASK1".equals(action)) {
            Log.d(TAG, "handle task: " + action);
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "service onDestroy: ");
        super.onDestroy();
    }
}

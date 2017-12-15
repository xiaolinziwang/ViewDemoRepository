package com.zhugefang.doublephone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private TelephonyManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            manager = ((TelephonyManager) getSystemService(TELECOM_SERVICE));
            Class aClass = manager.getClass();
//            Method getImei = aClass.getDeclaredMethod("getImei", int.class);
            Method getLine1NumberForSubscriber = aClass.getDeclaredMethod("getLine1NumberForSubscriber", int.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

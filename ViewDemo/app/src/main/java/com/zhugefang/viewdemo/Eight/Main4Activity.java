package com.zhugefang.viewdemo.Eight;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhugefang.viewdemo.R;

public class Main4Activity extends WindowActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Dialog dialog = new Dialog(this);
        dialog.cancel();
    }
}

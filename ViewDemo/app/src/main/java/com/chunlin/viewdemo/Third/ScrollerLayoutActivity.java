package com.chunlin.viewdemo.Third;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chunlin.viewdemo.R;

public class ScrollerLayoutActivity extends BaseActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn = ((Button) findViewById(R.id.btn));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams)btn.getLayoutParams();
                Log.d(TAG, "onClick: layoutParams.width:"+layoutParams.width);
                layoutParams.width+=100;
                layoutParams.leftMargin+=100;
                Log.d(TAG, "onClick: btn.getWidth():"+btn.getWidth());

                btn.setLayoutParams(layoutParams);
                Log.d(TAG, "onClick: layoutParams.width=+100:"+layoutParams.width);
            }
        });
    }

    @Override
    public int getLayoutId() {
//        return R.layout.activity_scroller_layout;
        return R.layout.activity_scroller_layout1;
    }
}

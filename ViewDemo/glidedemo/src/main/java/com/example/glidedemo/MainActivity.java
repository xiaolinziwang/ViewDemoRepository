package com.example.glidedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = ((ImageView) findViewById(R.id.iv));

    }
    public void loadPic(View view){
        Glide.with(this).load("https://www.baidu.com/img/bd_logo1.png").skipMemoryCache(true)
             .placeholder(R.mipmap.ic_launcher).into(mImageView);
    }
}

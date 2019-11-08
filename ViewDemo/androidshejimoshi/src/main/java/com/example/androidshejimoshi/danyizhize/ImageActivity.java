package com.example.androidshejimoshi.danyizhize;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import com.example.androidshejimoshi.R;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ImageView imageView = (ImageView) findViewById(R.id.iv);
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.displayImage("https://www.baidu.com/img/bd_logo1.png", imageView);
    }
}

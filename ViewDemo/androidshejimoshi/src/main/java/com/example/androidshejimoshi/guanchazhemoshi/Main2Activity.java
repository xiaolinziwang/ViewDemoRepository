package com.example.androidshejimoshi.guanchazhemoshi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.androidshejimoshi.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //被观察者
        DevObservable observable = new DevObservable();
        //观察者
        CoderObserver xiaodong = new CoderObserver("小董");
        CoderObserver laogong = new CoderObserver("老公");
        //被观察者添加观察者
        observable.addObserver(xiaodong);
        observable.addObserver(laogong);
        observable.postNews("love~love~love~love~love~love");
    }
}

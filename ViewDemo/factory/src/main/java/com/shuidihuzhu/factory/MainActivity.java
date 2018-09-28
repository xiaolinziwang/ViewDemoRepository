package com.shuidihuzhu.factory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.shuidihuzhu.factory.abstractfactory.BikeFactory;
import com.shuidihuzhu.factory.abstractfactory.BusFactory;
import com.shuidihuzhu.factory.abstractfactory.ICarFactory;
import com.shuidihuzhu.factory.builder.Person1;
import com.shuidihuzhu.factory.method.Bike;
import com.shuidihuzhu.factory.method.Bus;
import com.shuidihuzhu.factory.method.Car;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //工厂方法模式
        new Bike().gotowork();
        new Bus().gotowork();
        //抽象工厂
        ICarFactory factory = null;

        factory = new BikeFactory();
        Car bike = factory.getCar();
        bike.gotowork();

        factory = new BusFactory();
        Car bus = factory.getCar();
        bus.gotowork();
        //builder
        Person1 person1 = new Person1.Builder(1, "张三").age(18).sex("男").desc("测试使用builder模式").build();
    }
}

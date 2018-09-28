package com.shuidihuzhu.factory.abstractfactory;

import com.shuidihuzhu.factory.method.Bike;
import com.shuidihuzhu.factory.method.Car;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2018/9/25 下午5:14
 * 修改人：chunlinwang
 * 修改时间：2018/9/25 下午5:14
 * 修改备注：
 */
public class BikeFactory implements ICarFactory {
    @Override
    public Car getCar() {
        return new Bike();
    }
}

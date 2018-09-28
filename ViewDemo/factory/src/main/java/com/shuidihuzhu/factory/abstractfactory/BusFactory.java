package com.shuidihuzhu.factory.abstractfactory;

import com.shuidihuzhu.factory.method.Bus;
import com.shuidihuzhu.factory.method.Car;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2018/9/25 下午5:15
 * 修改人：chunlinwang
 * 修改时间：2018/9/25 下午5:15
 * 修改备注：
 */
public class BusFactory implements ICarFactory {
    @Override
    public Car getCar() {
        return new Bus();
    }
}

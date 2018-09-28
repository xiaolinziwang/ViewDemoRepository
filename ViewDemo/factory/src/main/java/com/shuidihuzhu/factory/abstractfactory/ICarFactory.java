package com.shuidihuzhu.factory.abstractfactory;

import com.shuidihuzhu.factory.method.Car;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2018/9/25 下午4:49
 * 修改人：chunlinwang
 * 修改时间：2018/9/25 下午4:49
 * 修改备注：抽象的工厂接口
 */
public interface ICarFactory {
    Car getCar();
}

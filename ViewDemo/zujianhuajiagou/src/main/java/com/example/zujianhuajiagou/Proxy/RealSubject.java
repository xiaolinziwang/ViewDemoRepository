package com.example.zujianhuajiagou.Proxy;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2019-11-13 22:44
 * 修改人：chunlinwang
 * 修改时间：2019-11-13 22:44
 * 修改备注：
 */
public class RealSubject implements Subject {
    @Override
    public void doSomething() {
        System.out.println("call doSomething()");
    }
}

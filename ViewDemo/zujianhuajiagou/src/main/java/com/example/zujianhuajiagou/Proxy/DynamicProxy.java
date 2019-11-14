package com.example.zujianhuajiagou.Proxy;

import java.lang.reflect.Proxy;

/**
 * 项目名称：ViewDemo
 * 类描述：通过 Proxy新建代理类对象:
 * 创建人：chunlinwang
 * 创建时间：2019-11-13 22:50
 * 修改人：chunlinwang
 * 修改时间：2019-11-13 22:50
 * 修改备注：通过 Proxy新建代理类对象:
 * 动态代理的作用在于在不修改源码的情况下，可以增强 一些方法，在方法执行前后做任何 想做的事情。
 */
public class DynamicProxy {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        Subject proxySubject = (Subject) Proxy
                .newProxyInstance(Subject.class.getClassLoader(), new Class[] {
                        Subject.class }, new ProxyHandler(realSubject));
        proxySubject.doSomething();
    }
}

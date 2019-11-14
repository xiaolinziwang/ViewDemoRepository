package com.example.zujianhuajiagou.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2019-11-13 22:46
 * 修改人：chunlinwang
 * 修改时间：2019-11-13 22:46
 * 修改备注：
 */
public class ProxyHandler implements InvocationHandler {
    Object mRealSubject;


    public ProxyHandler(Object realSubject) {
        this.mRealSubject = realSubject;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在转调具体目标对象之前，可以执行一些功能处 理
        //调 用具体目标对象的方法
        System.out.println("before invoke(mRealSubject, args)");
        Object result = method.invoke(mRealSubject, args);
        System.out.println("after invoke(mRealSubject, args)");
        //在转调具体目标对 象之后， 可以执行一些功能处理
        return result;
    }
}

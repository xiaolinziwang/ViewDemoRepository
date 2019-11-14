package com.example.zujianhuajiagou;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2019-11-13 22:18
 * 修改人：chunlinwang
 * 修改时间：2019-11-13 22:18
 * 修改备注：
 */
public class TestT<T> {
    public void test(T t) {
        if (t instanceof Integer){
            System.out.println("Integer");
        }
        if (t instanceof Object){
            System.out.println("Object");
        }
        System.out.println(t);
    }
}

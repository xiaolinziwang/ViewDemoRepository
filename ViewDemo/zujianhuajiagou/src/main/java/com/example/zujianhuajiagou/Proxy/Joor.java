package com.example.zujianhuajiagou.Proxy;

import static org.joor.Reflect.on;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2019-11-14 11:07
 * 修改人：chunlinwang
 * 修改时间：2019-11-14 11:07
 * 修改备注：
 */
public class Joor {
    public static void testJoor() {
        //
        //String world = on("java.lang.String")  // Like Class.forName()
        //                                       .create("Hello World") // Call most specific matching constructor
        //                                       .call("substring", 6)  // Call most specific matching substring() method
        //                                       .call("toString")      // Call toString()
        //                                       .get();                // Get the wrapped object, in this case a String
        String world = on("java.lang.String").create("hello world").call("substring", 6)
                                         .call("toString").get();
        System.out.println(world);
        String substring = on("java.lang.String")
                .create("Hello World")
                .as(JoorStringProxy.class) // Create a proxy for the wrapped object
                .substring(6);         // Call a proxy method
        System.out.println(substring);
    }


    public static void main(String[] args) {testJoor();}
}


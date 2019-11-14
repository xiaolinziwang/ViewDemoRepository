package com.example.zujianhuajiagou;

import java.lang.reflect.Method;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2019-11-13 21:56
 * 修改人：chunlinwang
 * 修改时间：2019-11-13 21:56
 * 修改备注：
 */
public class Test {
    public static void main(String[] args){
        try {
            Class<?> clz = Class.forName("com.example.zujianhuajiagou.TestMethod");
            Method method = clz.getDeclaredMethod("test");
            method.invoke(null);//syst test
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

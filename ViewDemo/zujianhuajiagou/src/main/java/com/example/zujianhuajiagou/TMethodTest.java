package com.example.zujianhuajiagou;

import java.lang.reflect.Method;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2019-11-13 22:19
 * 修改人：chunlinwang
 * 修改时间：2019-11-13 22:19
 * 修改备注：
 */
public class TMethodTest {
    public static void main(String[] args) {
        try {
            Class<TestT> clzT = TestT.class;
            Method tm = clzT.getDeclaredMethod("test", Object.class);
            //取消方法的权限检查，并不是将private改为public
            tm.setAccessible(true);
            tm.invoke(new TestT<Integer>(), 1);//syst 1

            //
            //Class<?> clz = Class.forName("com.example.zujianhuajiagou.TestMethod");
            //Method method = clz.getDeclaredMethod("test");
            //method.invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

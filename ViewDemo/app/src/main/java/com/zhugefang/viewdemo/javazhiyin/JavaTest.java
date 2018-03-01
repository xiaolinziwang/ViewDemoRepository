package com.zhugefang.viewdemo.javazhiyin;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2018/1/24 11:19
 * 修改人：Administrator
 * 修改时间：2018/1/24 11:19
 * 修改备注：
 */
public class JavaTest {
    public static void main(String[] args) {
        //Bread bread1 = new Bread("bread1");
        //Bread bread2 = new Bread("bread2");
        //Bread is loaded
        // meal
        // bread1
        // meal
        // bread2
        BreadChild bread2 = new BreadChild("bread2");
        //Bread is loaded
        //        bread
        //        bread2
        //       BreadChild
        //        bread2
    }
}

class Bread {
    public Bread(String name) {
        System.out.println("父亲：" + name);
    }


    static {
        System.out.println("Bread is loaded");
    }


    Meal bread = new Meal("bread");
}

class BreadChild extends Bread {
    public BreadChild(String name) {
        super(name);
        System.out.println("孩子：" + name);
    }


    Meal bread2 = new Meal("BreadChild");
}

class Meal {
    public Meal(String name) {
        System.out.println(name);
    }
}

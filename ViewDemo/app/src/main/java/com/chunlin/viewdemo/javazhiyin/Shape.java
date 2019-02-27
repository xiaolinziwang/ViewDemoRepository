package com.chunlin.viewdemo.javazhiyin;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2018/1/24 12:22
 * 修改人：Administrator
 * 修改时间：2018/1/24 12:22
 * 修改备注：
 */
public class Shape {
    protected String name;



    public Shape(String name) {
        this.name = name;
    }


    private void write() {
        System.out.println("我是private write");
    }


    protected void write1() {
        System.out.println("我是protected write");
    }
}

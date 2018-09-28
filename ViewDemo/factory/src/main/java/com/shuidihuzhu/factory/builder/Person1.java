package com.shuidihuzhu.factory.builder;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2018/9/25 下午6:44
 * 修改人：chunlinwang
 * 修改时间：2018/9/25 下午6:44
 * 修改备注：
 */
public class Person1 {
    //必要参数
    private final int id;
    private final String name;
    //可选参数
    private final int age;
    private final String sex;
    private final String phone;
    private final String address;
    private final String desc;


    private Person1(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.age = builder.age;
        this.sex = builder.sex;
        this.phone = builder.phone;
        this.address = builder.address;
        this.desc = builder.desc;
    }


    public static class Builder {
        //必要参数
        private final int id;
        private final String name;
        //可选参数
        private int age;
        private String sex;
        private String phone;
        private String address;
        private String desc;


        public Builder(int id, String name) {
            this.id = id;
            this.name = name;
        }


        public Builder age(int val) {
            this.age = val;
            return this;
        }


        public Builder sex(String val) {
            this.sex = val;
            return this;
        }


        public Builder phone(String val) {
            this.phone = val;
            return this;
        }


        public Builder address(String val) {
            this.address = val;
            return this;
        }


        public Builder desc(String val) {
            this.desc = val;
            return this;
        }


        public Person1 build() {
            return new Person1(this);
        }
    }
}

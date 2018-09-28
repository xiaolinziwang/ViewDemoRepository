package com.shuidihuzhu.factory.builder;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2018/9/25 下午5:28
 * 修改人：chunlinwang
 * 修改时间：2018/9/25 下午5:28
 * 修改备注：
 */
public class Person {
    String lastName;
    String firstName;
    String middleName;


    public Person(String lastName, String firstName, String middleName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }


    public static class PersonBuilder {
        String lastName;
        String firstName;
        String middleName;


        public PersonBuilder() {}


        public PersonBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }


        public PersonBuilder setFirstName(String firstName) {
            this.firstName = lastName;
            return this;
        }


        public PersonBuilder setmiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }


        public Person createPerson() {
            return new Person(lastName, firstName, middleName);
        }
    }
}

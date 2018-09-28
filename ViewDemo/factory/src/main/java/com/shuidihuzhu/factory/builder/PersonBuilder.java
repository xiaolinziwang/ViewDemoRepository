package com.shuidihuzhu.factory.builder;

/**
 * 项目名称：ViewDemo
 * 类描述：使用builder模式处理需要很多参数的构造函数
 * 创建人：chunlinwang
 * 创建时间：2018/9/25 下午5:31
 * 修改人：chunlinwang
 * 修改时间：2018/9/25 下午5:31
 * 修改备注：
 */
public class PersonBuilder {
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

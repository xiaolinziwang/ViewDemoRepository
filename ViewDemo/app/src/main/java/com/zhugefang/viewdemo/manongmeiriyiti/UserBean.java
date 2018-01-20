package com.zhugefang.viewdemo.manongmeiriyiti;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2018/1/20 14:22
 * 修改人：Administrator
 * 修改时间：2018/1/20 14:22
 * 修改备注：
 */
public class UserBean {
    public String name;
    public int age;
    public String pwd;


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UserBean)) {
            return false;
        }
        UserBean userBean = (UserBean) obj;
        return ((userBean.name.equals(name)) && (userBean.age == age) &&
                        (userBean.pwd.equals(pwd)));
    }


    /*《Effective java书中》给出的一种算法，基于17和31散列码思想的实现*/
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + age;
        result = 31 * result + pwd.hashCode();
        return result;
        //jdk7.0开始
        //Objects.hashCode(name,age,pwd);
    }
}

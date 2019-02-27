package com.chunlin.viewdemo.manongmeiriyiti;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2018/1/20 12:36
 * 修改人：Administrator
 * 修改时间：2018/1/20 12:36
 * 修改备注：
 */
public class HashMapTest {
    public static void main(String[] args) {
        Entity entity = new Entity();
        entity.setName("张三");
        Entity entity1 =new Entity();
        entity1.setName("张三");
        //hashCode好像是10位数
        System.out.println("entity:hashcode:" + entity.hashCode());//1163157884
        System.out.println("entity1:hashcode:" + entity1.hashCode());//1956725890
        System.out.println("equals:" + entity.equals(entity1));//false
        System.out.println("==:" + (entity == entity1));//false
        System.out.println("entity.getName:" +entity.getName());
        System.out.println("entity1.getName:" +entity1.getName());
    }
}

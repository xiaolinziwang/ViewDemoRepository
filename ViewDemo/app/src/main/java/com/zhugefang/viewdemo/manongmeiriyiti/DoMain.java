package com.zhugefang.viewdemo.manongmeiriyiti;

/**
 * Created by Administrator on 2017/12/18.
 * Comparable可以认为是一个内比较器，实现了Comparable接口的类有一个特点，就是这些类是可以和自己比较的，
 * 至于具体和另一个实现了Comparable接口的类如何比较，则依赖compareTo方法的实现，
 * compareTo方法也被称为自然比较方法。如果开发者add进入一个Collection的对象想要Collections的sort方法帮你自动进行排序的话，
 * 那么这个对象必须实现Comparable接口。compareTo方法的返回值是int，有三种情况：
 *
 * 1、比较者大于被比较者（也就是compareTo方法里面的对象），那么返回正整数
 *
 * 2、比较者等于被比较者，那么返回0
 *
 * 3、比较者小于被比较者，那么返回负整数
 * https://www.cnblogs.com/szlbm/p/5504634.html
 */

public class DoMain implements Comparable<DoMain> {
    public String mString;


    public DoMain(String string) {
        this.mString = string;
    }


    public String getString() {
        return mString;
    }


    @Override
    public int compareTo(DoMain doMain) {
        //if (this.mString.compareTo(doMain.mString) > 0) {
        //    return 1;
        //} else if (this.mString.compareTo(doMain.mString) == 0) {
        //    return 0;
        //} else {
        //    return -1;
        //}
        return this.mString.compareTo(doMain.mString);
    }
}

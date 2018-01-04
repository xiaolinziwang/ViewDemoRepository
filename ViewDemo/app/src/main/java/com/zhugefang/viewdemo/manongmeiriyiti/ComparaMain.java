package com.zhugefang.viewdemo.manongmeiriyiti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/12/18.
 * Comparator
 *
 * Comparator可以认为是是一个外比较器，个人认为有两种情况可以使用实现Comparator接口的方式：
 *
 * 1、一个对象不支持自己和自己比较（没有实现Comparable接口），但是又想对两个对象进行比较
 *
 * 2、一个对象实现了Comparable接口，但是开发者认为compareTo方法中的比较方式并不是自己想要的那种比较方式
 *
 * Comparator接口里面有一个compare方法，方法有两个参数T o1和T o2，是泛型的表示方式，
 * 分别表示待比较的两个对象，方法返回值和Comparable接口一样是int，有三种情况：
 *
 * 1、o1大于o2，返回正整数
 *
 * 2、o1等于o2，返回0
 *
 * 3、o1小于o3，返回负整数
 * Comparable是排序接口。若一个类实现了Comparable接口，就意味着该类支持排序。
 * 实现了Comparable接口的类的对象的列表或数组可以通过Collections.sort或Arrays.sort进行自动排序。
 * 总结：
 * Comparable是排序接口，若一个类实现了Comparable接口，就意味着“该类支持排序”。而Comparator是比较器，我们若需要控制某个类的次序，可以建立一个“该类的比较器”来进行排序。
 * Comparable相当于“内部比较器”，而Comparator相当于“外部比较器”。
 * 两种方法各有优劣， 用Comparable 简单， 只要实现Comparable 接口的对象直接就成为一个可以比较的对象，
 * 但是需要修改源代码。 用Comparator 的好处是不需要修改源代码， 而是另外实现一个比较器，
 * 当某个自定义的对象需要作比较的时候，把比较器和对象一起传递过去就可以比大小了，
 * 并且在Comparator 里面用户可以自己实现复杂的可以通用的逻辑，
 * 使其可以匹配一些比较简单的对象，那样就可以节省很多重复劳动了。
 */

public class ComparaMain {
    public static void main(String[] args) {
        //DoMain d1 = new DoMain("c");
        //DoMain d2 = new DoMain("c");
        //DoMain d3 = new DoMain("b");
        //DoMain d4 = new DoMain("d");
        //System.out.println(d1.compareTo(d2));
        //System.out.println(d1.compareTo(d3));
        //System.out.println(d1.compareTo(d4));
        //集合的排序
        //List<DoMain> list = new ArrayList<>();
        //list.add(d1);
        //list.add(d2);
        //list.add(d3);
        //list.add(d4);
        //System.out.println("排序前");
        //for (DoMain doMain : list) {
        //    System.out.println(doMain.getString());
        //}
        //DomainComparator domainComparator = new DomainComparator();
        ////Collections.sort(list);
        //Collections.sort(list,domainComparator);
        //System.out.println("排序后");
        //for (DoMain doMain : list) {
        //    System.out.println(doMain.getString());
        //}
        //DomainComparator domainComparator = new DomainComparator();
        //System.out.println(domainComparator.compare(d1,d2));
        //System.out.println(domainComparator.compare(d1,d3));
        //System.out.println(domainComparator.compare(d1,d4));
        //数组的排序
        //DoMain[] doMains = { d1, d2, d3, d4 };
        //System.out.println("排序前");
        //for (DoMain doMain : doMains) {
        //    System.out.println(doMain.getString());
        //}
        ////Arrays.sort(doMains);
        //Arrays.sort(doMains, new DomainComparator());
        //System.out.println("排序后");
        //for (DoMain doMain : doMains) {
        //    System.out.println(doMain.getString());
        //}
        //collection
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(0);
        list.add(9);
        list.add(8);
        List<Integer> list1 = new ArrayList<>();
        list1.add(100);
        list1.add(200);
        //Collections.sort(list);//按值大小排序
        //Collections.reverse(list);//将list顺序倒叙，
        Collections.fill(list, 100);//将数据全部装成100
        Collections.
        for (Integer i : list) {
            System.out.println(i + "");
        }
    }
}

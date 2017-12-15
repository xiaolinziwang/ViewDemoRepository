package com.zhugefang.viewdemo.manongmeiriyiti;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/8.
 * Iterator是ArrayList的内部类，ArrayList继承自AbstractList
 */

public class Demo {
    public static void main(String[] args) {
        //        Integer[] data = {1, 2, 3, 4};
        //        List<Integer> list = Arrays.asList(data);
        //        List<Integer> list = Arrays.asList(1,2,3);
        //        System.out.println(list.size());
        //        ArrayList<String> list = new ArrayList<>();
        //        list.add("c");
        //        list.add("c+++++++++");
        //        list.add("android1");
        //        list.add("android");
        //        list.add("android");
        //        1、
        //        Vector<String> vector = new Vector<>();
        //        vector.add("android1");
        //        vector.add("android");
        ////        System.out.println(list.equals(vector));
        //        2、
        //        ListIterator<String> iterator = list.listIterator();
        //        while (iterator.hasNext()){
        //            System.out.println("正序："+iterator.next()+","+iterator.nextIndex());
        //        }
        //        iterator.add("android2");//改掉的是当前index的值，而不是index增加,添加用add
        //        iterator.set("android2");//改掉的是当前index的值，而不是index增加,添加用add
        //        while (iterator.hasPrevious()){
        //            System.out.println("倒序："+iterator.previous()+","+iterator.previousIndex());
        //        }
        //        Set<String> stringSet = new HashSet<>();
        //        stringSet.iterator();
        //       3、
        //        ArrayList<String> list = new ArrayList<>();
        //        list.add("c");
        //        list.add("c+++++++++");
        //        list.add("android1");
        //        list.add("android");
        //        list.add("android");
        //
        //        for (int i = list.size() - 1; i >= 0; i--) {
        //            if ("android".equals(list.get(i))){
        //                list.remove("android");
        //            }
        //        }

        //        for (int i = 0; i < list.size(); i++) {
        //            String s = list.get(i);
        //            if ("android".equals(s)) {
        //                list.remove(s);
        //                i--;
        //            }
        //        }
        //        for (int i = list.size() - 1; i >= 0; i--) {
        //            String s = list.get(i);
        //            if ("android".equals(s)) {
        //                list.remove(s);
        //            }
        //        }
        //for是一个迭代器的循环，该循环直接报错
        //        for (String s : list) {
        //            list.remove(s);
        //        }
        //        for (String s : list) {
        //            System.out.println(s);
        //        }
        //        4、ArrayList扩容
        //        ArrayList<String> arrayList = new ArrayList<>();

        List<String> list = new ArrayList<>();
        list.add("android");
        list.add("java");

        List<String> list1 = new ArrayList<>(list);
        ////java.util.ArrayList$SubList cannot be cast to java.util.ArrayList,会报这个异常
        //List<String> list2 = ((ArrayList<String>) list.subList(0, list.size()));
        List<String> list2 = list.subList(0, list.size());
        list2.add("unix C");
/*subList产生的集合列表只是一个视图，所有的修改操作都会作用于原集合上*/
        System.out.println(list.equals(list1));//false
        System.out.println(list.equals(list2));//true
        //通过list来删，报错，modifiedException,切记通过subList生成子列表后不要再操作原列表
        //list2.subList(0, 1).clear();
    }
}

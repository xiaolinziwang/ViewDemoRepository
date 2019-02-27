package com.chunlin.viewdemo.manongmeiriyiti;

import java.util.PriorityQueue;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2018/1/18 12:34
 * 修改人：Administrator
 * 修改时间：2018/1/18 12:34
 * 修改备注：
 */
public class PriorityQueueTest {
    String TAG = this.getClass().getSimpleName();


    public static void main(String[] args) {
        PriorityQueue<Integer> integers = new PriorityQueue<>();
        integers.add(1);
        integers.add(6);
        integers.add(2);
        integers.add(7);
        integers.add(10);
        integers.add(3);
        for (Integer integer : integers) {
            System.out.println(integer + "");
        }
    }
}

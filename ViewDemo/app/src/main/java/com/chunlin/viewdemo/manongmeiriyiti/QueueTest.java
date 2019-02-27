package com.chunlin.viewdemo.manongmeiriyiti;

import java.util.*;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2018/1/17 11:55
 * 修改人：Administrator
 * 修改时间：2018/1/17 11:55
 * 修改备注：
 */
public class QueueTest {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        //将指定元素加入到队列尾部
        queue.add("aaaa");
        ////将指定元素加入到队列尾部,内部调用的是add(xxx)方法
        queue.offer("aaaaaaaaaaa");
        //内部调用getFirst(),获取队列头部元素，但是不删除该元素，如果队列为空抛出NoSuchElementException异常
        queue.element();
        // final Node<E> f = first;
        //return (f == null) ? null : f.item;
        //其实就是对element()的优化，如果为空，就手动赋值null,不删除元素
        queue.peek();
        // final Node<E> f = first;
        //return (f == null) ? null : unlinkFirst(f);
        //如果为空，就手动返回null，不为空时，就先取得第一个数据节点的元素，然后删除该节点
        queue.poll();
        //removeFirst(),如果为空，就抛异常，否则就调用unlinkFirst(f)，即，先取得第一个数据节点的元素，然后删除该节点
        queue.remove();
    }
}

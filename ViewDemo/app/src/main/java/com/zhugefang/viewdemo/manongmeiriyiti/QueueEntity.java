package com.zhugefang.viewdemo.manongmeiriyiti;

import java.util.LinkedList;

/**
 * 项目名称：ViewDemo
 * 类描述：使用LinkedList模拟一个堆栈或者队列的数据结构，LinkedList不但实现了List接口，还实现了
 * Deque双端队列接口
 *
 * 创建人：Administrator
 * 创建时间：2018/1/16 12:44
 * 修改人：Administrator
 * 修改时间：2018/1/16 12:44
 * 修改备注：
 */
public class QueueEntity {
    private LinkedList mLinkedList;


    public QueueEntity(LinkedList linkedList) {
        mLinkedList = linkedList;
    }


    public void push(Object object) {
        mLinkedList.addLast(object);
    }


    public Object pop() {
        return mLinkedList.removeFirst();//队列，先进先出
        //return mLinkedList.removeLast();//堆栈，后进先出
    }


    public boolean isEmpty() {
        return mLinkedList.isEmpty();
    }
}

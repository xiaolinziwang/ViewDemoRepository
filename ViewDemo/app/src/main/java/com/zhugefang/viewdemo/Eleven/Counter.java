package com.zhugefang.viewdemo.Eleven;

/**
 * Created by Administrator on 2018/1/6.
 * 同步线程
 * //2、当一个线程访问对象的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该对象中的
 * //    非synchronize(this)同步代码块
 */

public class Counter implements Runnable {
    private int count;


    public Counter() {
        count = 0;
    }


    public void countAdd() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //非synchronized代码块，未对count进行读写，所以可以不用synchronized
    public void printCount() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + " count:" + count);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        if (name.equals("A")) {
            countAdd();
        } else if (name.equals("B")) {
            printCount();
        }
    }


    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread thread1 = new Thread(counter, "A");
        Thread thread2 = new Thread(counter, "B");
        thread1.start();
        thread2.start();
    }
}

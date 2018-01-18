package com.zhugefang.viewdemo.Eleven;

/**
 * Created by Administrator on 2018/1/4.
 * http://blog.csdn.net/luoweifu/article/details/46613015
 * synchronized
 * 同步线程
 *   //1.一个线程访问一个对象中的synchronized(this)同步代码块时，
 // 其他试图访问该对象的线程将被阻塞。我们看下面一个例子：
 */

public class SyncThread implements Runnable {
    private static int count;


    public SyncThread() {
        count = 0;
    }


    @Override
    public void run() {
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


    public int getCount() {
        return count;
    }


    public static void main(String[] args) {
        //1.一个线程访问一个对象中的synchronized(this)同步代码块时，
        // 其他试图访问该对象的线程将被阻塞。我们看下面一个例子：
        //第一种：
        SyncThread syncThread = new SyncThread();
        Thread syncThread1 = new Thread(syncThread, "SyncThread1");
        Thread syncThread2 = new Thread(syncThread, "SyncThread2");
        syncThread1.start();
        syncThread2.start();
        //第二种：
        //Thread syncThread1 = new Thread(new SyncThread(), "SyncThread1");
        //Thread syncThread2 = new Thread(new SyncThread(), "SyncThread2");
        //syncThread1.start();
        //syncThread2.start();

    }
}

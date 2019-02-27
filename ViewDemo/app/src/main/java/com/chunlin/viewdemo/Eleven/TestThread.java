package com.chunlin.viewdemo.Eleven;

/**
 * Created by chunLin on 2017/12/24.
 */

public class TestThread {
    ThreadLocal<Long> longLocal = new ThreadLocal<>();
    ThreadLocal<String> stringLocal = new ThreadLocal() {
        @Override
        protected Object initialValue() {
            return Thread.currentThread().getName();
        }
    };

    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public Long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final TestThread test = new TestThread();
//        test.set();
        System.out.println(test.getLong());
        System.out.println(test.getString());
        new Thread() {
            @Override
            public void run() {
                test.set();
                System.out.println(test.getLong());
                System.out.println(test.getString());
            }
        }.start();
        System.out.println(test.getLong());
        System.out.println(test.getString());
    }
}

package com.zhugefang.viewdemo.Eleven;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Administrator on 2018/1/4.
 */

public class BarWorker2 implements Runnable {
    private static AtomicBoolean exists = new AtomicBoolean(false);
    private String name;


    public BarWorker2(String name) {
        this.name = name;
    }


    @Override
    public void run() {
        if (exists.compareAndSet(false, true)) {//如果当前值 == 预期值，则以原子方式将该值设置为给定的更新值。
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " enter");
            try {
                System.out.println(name + " working");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " leave");
            exists.set(false);
        } else {
            System.out.println(name + " give up");
        }
    }


    public static void main(String[] args) {
        BarWorker2 bar1 = new BarWorker2("bar1");
        BarWorker2 bar2 = new BarWorker2("bar2");
        new Thread(bar1).start();
        new Thread(bar2).start();
    }
}


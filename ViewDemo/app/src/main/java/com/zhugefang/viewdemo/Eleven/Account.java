package com.zhugefang.viewdemo.Eleven;

/**
 * Created by Administrator on 2018/1/6.
 * 指定要给某个对象加锁
 * 银行客户
 * http://blog.csdn.net/luoweifu/article/details/46613015
 */

public class Account {
    String name;
    float mFloat;


    public Account(String name, float aFloat) {
        this.name = name;
        mFloat = aFloat;
    }


    //存钱
    public void deposit(float amt) {
        mFloat += amt;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //取钱
    public void withdraw(float amt) {
        mFloat -= amt;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public float getBalance() {
        return mFloat;
    }


    /*
    * 账户操作类
    * 当有一个明确的对象作为锁时，就可以用类似下面这样的方式写程序。
    * */
    static class AccountOperator implements Runnable {
        private Account mAccount;


        public AccountOperator(Account account) {
            mAccount = account;
        }


        @Override
        public void run() {
            synchronized (mAccount) {
                mAccount.deposit(500);
                mAccount.withdraw(500);
                System.out.println(
                        Thread.currentThread().getName() + ":" + mAccount.getBalance() + ",time:" +
                                System.currentTimeMillis());
            }
        }
    }
/*
* 当没有明确的对象作为锁，
* */


    /*
    * 调用方法
    * */
    public static void main(String[] args) {
        Account zhangSan = new Account("zhangSan", 1000f);
        AccountOperator accountOperator = new AccountOperator(zhangSan);
        final int THREAD_NUM = 5;
        Thread[] threads = new Thread[THREAD_NUM];
        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i] = new Thread(accountOperator, "Thread" + i);
            threads[i].start();
        }
    }
}

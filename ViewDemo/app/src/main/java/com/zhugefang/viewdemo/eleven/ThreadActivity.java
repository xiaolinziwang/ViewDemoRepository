package com.zhugefang.viewdemo.Eleven;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;
import com.zhugefang.viewdemo.R;
import com.zhugefang.viewdemo.Third.BaseActivity;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 直接调用线程的run方法，相当于是一个普通的类调用自己的方法，
 * 还是在当前线程执行，并不会开启新的线程
 */
public class ThreadActivity extends BaseActivity {

    private TextView btn;
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn = ((TextView) findViewById(R.id.tv));

        //        final ThreadLocal<Boolean> mBooleanThreadLocal = new ThreadLocal<>();
        //        mBooleanThreadLocal.set(true);
        //        mBooleanThreadLocal.set(false);
        //        Log.d(TAG, "[Thread # main ]: mBooleanThreadLocal=" + mBooleanThreadLocal.get());
        //        new Thread("Thread#1") {
        //            @Override
        //            public void run() {
        //                mBooleanThreadLocal.set(false);
        //                Log.d(TAG, "[Thread # 1 ]: mBooleanThreadLocal=" + mBooleanThreadLocal.get());
        //            }
        //        }.start();
        //        new Thread("Thread#2") {
        //            @Override
        //            public void run() {
        //                Log.d(TAG, "[Thread #2 ]: mBooleanThreadLocal=" + mBooleanThreadLocal.get());
        //            }
        //        }.start();
        //     *这种方式不报错也不起作用
        //        new Thread(new Runnable() {
        //            @Override
        //            public void run() {
        //                Log.d(TAG, "run: Thread");
        //                handler.post(new Runnable() {//将   m.callback = r;
        //                    @Override
        //                    public void run() {//其实执行的是HandlerMessage
        //                        //更新Ui
        //                        btn.setText("bad");
        //                        Log.d(TAG, "handler: " + Thread.currentThread().getName());
        //                    }
        //                });
        ////                Looper.prepare();
        ////                Handler handler = new Handler();
        ////                Looper.loop();
        ////                Log.d(TAG, "Thread--run: " + Thread.currentThread().getName());
        //                //这种方式，不能达到更新UI的目的
        ////                btn.post(new Runnable() {//主线程中执行
        ////                    @Override
        ////                    public void run() {
        ////                        btn.setText("bad");
        ////                        Log.d(TAG, "子线程post: " + Thread.currentThread().getName());
        ////                    }
        ////                });
        //                Log.d(TAG, "run: Thread1");
        //            }
        //        }).start();
        /*处理消息有3种方式
        *   public void dispatchMessage(Message msg) {
        if (msg.callback != null) {//第一种
            handleCallback(msg);
        } else {
            if (mCallback != null) {//第二种
                if (mCallback.handleMessage(msg)) {
                    return;
                }
            }
            handleMessage(msg);//第三种
        }
    }
        * */

        //第一种：通过m.callback = r; message.callback.run();处理小心
        //final Handler handler = new Handler();
        //new Thread() {
        //    @Override
        //    public void run() {
        //                handler.post(new Runnable() {//将   m.callback = r;
        //                    @Override
        //                    public void run() {//其实执行的是HandlerMessage
        //                        //更新Ui
        //                        Log.d(TAG, "handler: " + Thread.currentThread().getName());
        //                        btn.setText(Thread.currentThread().getName());
        //                    }
        //                });
        //                http://blog.csdn.net/chen_senhua/article/details/52202413
                /*该方法在某一些情况是不执行的*/
        //                btn.post(new Runnable() {
        //                    @Override
        //                    public void run() {
        //                        //更新Ui
        //                        Log.d(TAG, "handler: " + Thread.currentThread().getName());
        //                        btn.setText(Thread.currentThread().getName());
        //                    }
        //                });
        //        runOnUiThread(new Runnable() {
        //            @Override
        //            public void run() {
        //                //更新Ui
        //                Log.d(TAG, "handler: " + Thread.currentThread().getName());
        //                btn.setText(Thread.currentThread().getName());
        //            }
        //        });
        //    }
        //}.start();

        //        btn.post(new Runnable() {
        //            @Override
        //            public void run() {
        ////                btn.setText("哈哈哈");
        //                Log.d(TAG, "主线程post: " + Thread.currentThread().getName());
        //            }
        //        });
        //第二种：通过mCallback来处理消息
        //        Handler handler1 = new Handler(new Handler.Callback() {
        //            @Override
        //            public boolean handleMessage(Message msg) {
        //                //主线程
        //                return false;
        //            }
        //        });
        //Thread引起内存泄漏案例分析
        new MyThread(this).start();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main3;
    }


    public static boolean isInMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }


    //第三种
    static class MyHandler extends Handler {
        WeakReference<ThreadActivity> weakReference;


        public MyHandler(WeakReference<ThreadActivity> activity) {
            this.weakReference = activity;
        }


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ThreadActivity activity = weakReference.get();
            if (activity != null) {
                //更新UI
            }
        }
    }

    /*
    * 通过软引用的方式保存外部类的引用*/
    private static class MyThread extends Thread {
        SoftReference<ThreadActivity> context;


        public MyThread(ThreadActivity activity) {
            context = new SoftReference<ThreadActivity>(activity);
        }


        @Override
        public void run() {
            //SystemClock.sleep(1000);
            final ThreadActivity threadActivity = context.get();
            if (threadActivity != null) {
                threadActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        threadActivity.btn.setText("结束休息");
                        Toast.makeText(context.get(), "结束休息", Toast.LENGTH_LONG);
                    }
                });
            }
        }
    }
}

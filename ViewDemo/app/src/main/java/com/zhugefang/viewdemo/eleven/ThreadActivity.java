package com.zhugefang.viewdemo.eleven;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.zhugefang.viewdemo.R;
import com.zhugefang.viewdemo.Third.BaseActivity;

import java.lang.ref.WeakReference;

/**
 * 直接调用线程的run方法，相当于是一个普通的类调用自己的方法，
 * 还是在当前线程执行，并不会开启新的线程
 */
public class ThreadActivity extends BaseActivity {

    private Button btn;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn = ((Button) findViewById(R.id.btn));

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
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: Thread");
                handler.post(new Runnable() {//将   m.callback = r;
                    @Override
                    public void run() {//其实执行的是HandlerMessage
                        //更新Ui
                        btn.setText("bad");
                        Log.d(TAG, "handler: " + Thread.currentThread().getName());
                    }
                });
//                Looper.prepare();
//                Handler handler = new Handler();
//                Looper.loop();
//                Log.d(TAG, "Thread--run: " + Thread.currentThread().getName());
                //这种方式，不能达到更新UI的目的
//                btn.post(new Runnable() {//主线程中执行
//                    @Override
//                    public void run() {
//                        btn.setText("bad");
//                        Log.d(TAG, "子线程post: " + Thread.currentThread().getName());
//                    }
//                });
                Log.d(TAG, "run: Thread1");
            }
        }).start();
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
        Handler handler = new Handler();
        handler.post(new Runnable() {//将   m.callback = r;
            @Override
            public void run() {//其实执行的是HandlerMessage
                //更新Ui
                Log.d(TAG, "handler: " + Thread.currentThread().getName());
            }
        });
        btn.post(new Runnable() {
            @Override
            public void run() {
//                btn.setText("哈哈哈");
                Log.d(TAG, "主线程post: " + Thread.currentThread().getName());
            }
        });
//第二种：通过mCallback来处理消息
        Handler handler1 = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                //主线程
                return false;
            }
        });


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


}

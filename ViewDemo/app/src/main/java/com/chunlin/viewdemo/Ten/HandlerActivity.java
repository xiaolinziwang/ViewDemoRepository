package com.chunlin.viewdemo.Ten;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.chunlin.viewdemo.R;
import java.lang.ref.WeakReference;

public class HandlerActivity extends AppCompatActivity {
    private Handler myHandler = new MyHandler(this);
    private TextView tv_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        tv_button = ((TextView) findViewById(R.id.tv_button));
        tv_button.setText("包青天");
    }


    private static class MyHandler extends Handler {
        private WeakReference<HandlerActivity> mWeakReference;


        public MyHandler(HandlerActivity activity) {
            this.mWeakReference = new WeakReference<HandlerActivity>(activity);
        }


        @Override
        public void handleMessage(Message msg) {
            HandlerActivity activity = mWeakReference.get();
            if (activity != null) {
                activity.tv_button.setText("静态内部类的Handler");
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //对于Handler，还可以通过程序逻辑来进行保护
        //1、在关闭Activity的时候停掉你的后台线程。线程停掉了，就相当于切断了Handler和外部连接的线，Activity自然会在合适的时候被回收。
        //2、如果你的Handler是被delay的Message持有了引用，那么使用相应的Handler的removeCallbacks()方法，把消息对象从消息队列移除就行了
        if (myHandler!=null){
            myHandler.removeCallbacksAndMessages(null);
        }
    }
}

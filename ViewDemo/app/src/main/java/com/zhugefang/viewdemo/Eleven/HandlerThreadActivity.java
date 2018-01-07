package com.zhugefang.viewdemo.Eleven;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.zhugefang.viewdemo.R;
import com.zhugefang.viewdemo.Third.BaseActivity;

/*
* 使用HandlerThread的背景：
* 我们的app中每隔1分钟（合适的时间）去更新数据，然后更新我们的UI
* */
public class HandlerThreadActivity extends BaseActivity {
    private static final int MSG_UPDATE_INFO = 0x110;
    private TextView mTvServiceInfo;
    private HandlerThread mCheckMsgThread;
    private Handler mCheckMsgHandler;
    //与UI线程管理的handler
    private Handler mHandler = new Handler();
    public boolean isUpdateInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTvServiceInfo = (TextView) findViewById(R.id.id_textview);
        //创建后台线程
        initBackThread();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isUpdateInfo = true;
        mCheckMsgHandler.sendEmptyMessage(MSG_UPDATE_INFO);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isUpdateInfo = false;
        mCheckMsgHandler.sendEmptyMessage(MSG_UPDATE_INFO);
    }

    private void initBackThread() {
        mCheckMsgThread = new HandlerThread("check-message-coming");
        mCheckMsgThread.start();
        mCheckMsgHandler = new Handler(mCheckMsgThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                checkForUpdate();
                if (isUpdateInfo) {
                    mCheckMsgHandler.sendEmptyMessageDelayed(MSG_UPDATE_INFO, 1000);
                }
            }
        };
        new Thread("B"){
            @Override
            public void run() {
                super.run();
            }
        }.start();
    }


    /**
     * 模拟从服务器解析数据
     */
    private void checkForUpdate() {
        try {
            Thread.sleep(1000);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    String result = "实时更新中，当前大盘指数：<font color='red'>%d</font>";
                    result = String.format(result, ((int) (Math.random() * 3000 + 1000)));
                    mTvServiceInfo.setText(Html.fromHtml(result));

                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_handler_thread;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCheckMsgThread.quit();
    }
}

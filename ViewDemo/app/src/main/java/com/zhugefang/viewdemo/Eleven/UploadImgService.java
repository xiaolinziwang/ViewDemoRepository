package com.zhugefang.viewdemo.Eleven;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Administrator on 2018/1/8.
 * 好处：1、不需要自己去new Thread了
 * 2、不需要考虑在什么时候关闭该service了
 * 使用背景：
 * 耗时操作希望放到service中，service不能直接进行耗时操作，一般需要开启线程，
 * intentservice就提供了便利
 */

public class UploadImgService extends IntentService {
    private String TAG = this.getClass().getSimpleName();
    public static final String ACTION_IMG_UPLOAD = "img_upload";
    public static final String EXTRA_IMG_PATH = "img_path";


    public static void startUploadImg(Context context, String path) {
        Intent intent = new Intent(context, UploadImgService.class);
        intent.setAction(ACTION_IMG_UPLOAD);
        intent.putExtra(EXTRA_IMG_PATH, path);
        context.startService(intent);
    }


    public UploadImgService() {
        super("UploadImgService");
    }


    /*
    * 处理耗时任务
    * */
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (ACTION_IMG_UPLOAD.equals(action)) {
                String path = intent.getStringExtra(EXTRA_IMG_PATH);
                handleUploadImg(path);
            }
        }
    }


    private void handleUploadImg(String path) {
        //模拟耗时时间
        try {
            Thread.sleep(3000);
            Intent intent = new Intent(IntentServiceActivity.UPLOAD_RESULT);
            intent.putExtra(EXTRA_IMG_PATH, path);
            sendBroadcast(intent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}

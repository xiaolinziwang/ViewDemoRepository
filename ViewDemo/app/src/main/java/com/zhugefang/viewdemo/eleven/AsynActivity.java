package com.zhugefang.viewdemo.Eleven;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.zhugefang.viewdemo.R;
import com.zhugefang.viewdemo.Third.BaseActivity;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
* http://blog.csdn.net/kufeiyun/article/details/41477069
* http://www.importnew.com/18126.html
* */
public class AsynActivity extends BaseActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        button = ((Button) findViewById(R.id.execute));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这种事串行
//                new MyAsynTask("AsyncTask#1").execute("");
//                new MyAsynTask("AsyncTask#2").execute("");
//                new MyAsynTask("AsyncTask#3").execute("");
                new MyAsynTask("AsyncTask#4").execute("");
                new MyAsynTask("AsyncTask#1").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
                new MyAsynTask("AsyncTask#2").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
                new MyAsynTask("AsyncTask#3").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
                new MyAsynTask("AsyncTask#4").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_asyn;
    }

    private static class MyAsynTask extends AsyncTask<String, Integer, String> {
        private String mName = "AsyncTask";

        public MyAsynTask(String mName) {
            super();
            this.mName = mName;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(3000);
                publishProgress();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return mName;
        }

        @Override
        protected void onPostExecute(String result) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Log.d("MyAsynTask", result + "execute finish at: " + df.format(new Date()));
        }
    }
}

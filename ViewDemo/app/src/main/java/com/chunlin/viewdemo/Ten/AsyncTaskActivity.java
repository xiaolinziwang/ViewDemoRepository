package com.chunlin.viewdemo.Ten;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.chunlin.viewdemo.R;
import com.chunlin.viewdemo.Third.BaseActivity;

public class AsyncTaskActivity extends BaseActivity {

    private Button execute;
    private Button cancel;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        execute = (Button) findViewById(R.id.execute);
        cancel = (Button) findViewById(R.id.cancel);
        textView = (TextView) findViewById(R.id.text_view);

    }

    public class MyTask extends AsyncTask<String, Integer, String> {
        //onPreExecute方法用于在执行后台任务前做一些UI操作
        @Override
        protected void onPreExecute() {
            Log.d(TAG, "onPreExecute: ");
            textView.setText("loading......");
        }

        //doInBackground方法内部执行后台任务,不可在此方法内修改UI
        @Override
        protected String doInBackground(String... params) {
            Log.d(TAG, "doInBackground: ");
            publishProgress(10);
            return "正在使用中";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            textView.setText(s);
            textView.setEnabled(true);
            cancel.setEnabled(false);
            super.onPostExecute(s);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_async_task;
    }
}

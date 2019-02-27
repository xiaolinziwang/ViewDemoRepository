package com.chunlin.viewdemo.Eleven;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.chunlin.viewdemo.R;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
*http://blog.csdn.net/u012702547/article/details/52259529
* myThreadPool.execute(runnable),中new Thread（runnable）
 */
public class ThreadPoolExecutoActivity extends AppCompatActivity {
    private String TAG = this.getClass().getSimpleName();
    private ThreadPoolExecutor mThreadPoolExecutor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool_executo);
        mThreadPoolExecutor = new ThreadPoolExecutor(3, 5, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(128));
    }


    //使用1：
    public void addThread(View view) {
        for (int i = 0; i < 30; i++) {
            final int finalI = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(2000);
                    Log.d(TAG, "run: " + finalI);
                }
            };
            mThreadPoolExecutor.execute(runnable);
        }
    }


    //使用2
    public void submit(View view) {
        List<Future<String>> futures = new ArrayList<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        for (int i = 0; i < 10; i++) {
            Future<String> submit = threadPoolExecutor.submit(new MyTask(i));
            //将每一个任务执行的结果保存起来
            futures.add(submit);
        }
        //遍历所有任务的执行结果
        for (Future<String> future : futures) {
            try {
                Log.d(TAG, "submit: " + future.get() + ":" + future.isDone());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }


    //使用3：自定义线程池
    public void customThreadPool(View view) {
        MyThreadPool myThreadPool = new MyThreadPool(3, 5, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, Thread.currentThread().getName() + "------------run: " + finalI);
                }
            };
            myThreadPool.execute(runnable);
        }
    }


    public class MyThreadPool extends ThreadPoolExecutor {
        public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }


        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r);
            Log.d(TAG, "beforeExecute: 开始执行任务！");
        }


        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            Log.d(TAG, "afterExecute: 执行任务结束");
        }


        @Override
        protected void terminated() {
            super.terminated();
            //当调用shutdown（）或者shutDownNow()时会触发该方法
            Log.d(TAG, "terminated: 线程池关闭");
        }
    }

    class MyTask implements Callable<String> {
        private int taskId;


        public MyTask(int taskId) {
            this.taskId = taskId;
        }


        @Override
        public String call() throws Exception {
            Thread.sleep(1000);
            //返回每一个任务的执行结果
            return "call()方法被调用---" + Thread.currentThread().getName() + "------------" + taskId;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mThreadPoolExecutor.shutdown();
        //mThreadPoolExecutor.shutdownNow();
        //mThreadPoolExecutor.allowCoreThreadTimeOut(true);
    }
}

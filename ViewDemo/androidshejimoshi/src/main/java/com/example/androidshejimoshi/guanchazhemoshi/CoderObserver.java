package com.example.androidshejimoshi.guanchazhemoshi;

import android.util.Log;
import java.util.Observable;
import java.util.Observer;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2018/5/6 下午5:56
 * 修改人：chunlinwang
 * 修改时间：2018/5/6 下午5:56
 * 修改备注：
 */
public class CoderObserver implements Observer {
    private String TAG="CoderObserver";
    private String name;
    public CoderObserver(String name){
        this.name=name;
    }
    @Override
    public void update(Observable o, Object arg) {
        Log.d(TAG, "\nupdate: "+"HI,"+name+",DEVTechFrontier更新啦，内容："+arg+",Observable:"+o+"");
    }


    @Override
    public String toString() {
        return "码农："+name;
    }
}

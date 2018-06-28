package com.example.androidshejimoshi.guanchazhemoshi;

import java.util.Observable;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2018/5/6 下午6:02
 * 修改人：chunlinwang
 * 修改时间：2018/5/6 下午6:02
 * 修改备注：
 */
public class DevObservable extends Observable {
    public void postNews(String content){
        setChanged();
        notifyObservers(content);
    }
}

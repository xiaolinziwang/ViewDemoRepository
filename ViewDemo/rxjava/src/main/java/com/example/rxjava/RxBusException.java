package com.example.rxjava;

import com.jakewharton.rxrelay2.PublishRelay;
import com.jakewharton.rxrelay2.Relay;
import io.reactivex.Observable;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2018/5/14 上午11:56
 * 修改人：chunlinwang
 * 修改时间：2018/5/14 上午11:56
 * 修改备注：
 */
public class RxBusException {
    private static volatile RxBusException instance;
    private final Relay<Object> mBus;


    private RxBusException() {
        this.mBus = PublishRelay.create().toSerialized();
    }


    public static RxBusException getInstance() {
        if (instance == null) {
            if (instance == null) {
                synchronized (RxBusException.class) {
                    if (instance == null) {
                        instance = Holder.BUS;
                    }
                }
            }
        }
        return instance;
    }


    public void post(Object obj) {
        mBus.accept(obj);
    }


    public <T> Observable<T> toObservable(Class<T> tClass) {
        return mBus.ofType(tClass);
    }


    public Observable<Object> toObservable() {return mBus;}


    public boolean hasObservers() {
        return mBus.hasObservers();
    }


    private static class Holder {
        private static final RxBusException BUS = new RxBusException();
    }
}

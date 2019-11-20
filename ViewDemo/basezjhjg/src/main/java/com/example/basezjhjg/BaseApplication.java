package com.example.basezjhjg;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseApplication extends Application {
    private List<Class<? extends BaseAppLogic>> logicList = new ArrayList<>();
    private List<BaseAppLogic> logicClassList = new ArrayList<>();


    @Override
    public void onCreate() {
        super.onCreate();
        initLogic();
        logicCreate();
    }


    private void logicCreate() {
        for (Class<? extends BaseAppLogic> logicClass : logicList) {
            //使用反射初始化调用
            try {
                BaseAppLogic appLogic = logicClass.newInstance();
                logicClassList.add(appLogic);
                appLogic.onCreate();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        for (BaseAppLogic logic : logicClassList) {
            logic.onterminate();
        }
    }


    //主module的application调用
    protected abstract void initLogic();


    protected void registerApplicationLogic(Class<? extends BaseAppLogic> logicClass) {
        logicList.add(logicClass);
    }
}

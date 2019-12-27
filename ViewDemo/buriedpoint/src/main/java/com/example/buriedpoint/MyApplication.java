package com.example.buriedpoint;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2019-12-27 13:34
 * 修改人：chunlinwang
 * 修改时间：2019-12-27 13:34
 * 修改备注：
 */

import android.app.Application;
import com.example.sdk.SensorsDataAPI;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initSensorsDataAPI(this);
    }


    /**
     * 初始化埋点 SDK
     *
     * @param application Application
     */
    private void initSensorsDataAPI(Application application) {
        SensorsDataAPI.init(application);
    }
}


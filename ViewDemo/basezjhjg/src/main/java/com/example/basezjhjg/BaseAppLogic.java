package com.example.basezjhjg;

import android.content.res.Configuration;
import android.support.annotation.NonNull;

public class BaseAppLogic {
    protected BaseApplication mApplication;

    public BaseAppLogic() {
    }

    public void setApplication(@NonNull BaseApplication mApplication) {
        this.mApplication = mApplication;
    }

    public void onConfigurationChanged(Configuration newConfig) {

    }

    public void onTriMemory(int level) {

    }

    public void onLowMemory() {

    }

    public void onterminate() {

    }

    public void onCreate() {

    }
}

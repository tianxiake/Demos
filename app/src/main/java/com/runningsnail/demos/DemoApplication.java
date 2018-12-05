package com.runningsnail.demos;

import android.app.Application;
import android.os.StrictMode;

/**
 * @author liuyongjie create on 2018/12/5.
 */
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());
    }
}

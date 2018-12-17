package com.runningsnail.demos.activity.viewanimation.interceptor;

import android.graphics.Path;
import android.view.animation.PathInterpolator;

import com.runningsnail.demos.utils.HiLog;

/**
 * 自定义一个Path
 * @author yongjie created on 2018/12/9.
 */
public class MyPathInterceptor extends PathInterpolator {

    public static final String TAG = "MyPathInterceptor";

    public MyPathInterceptor(Path path) {
        super(path);
    }

    public MyPathInterceptor(float controlX, float controlY) {
        super(controlX, controlY);
    }


    @Override
    public float getInterpolation(float t) {
        HiLog.d(TAG, "t=" + t);
        return super.getInterpolation(t);
    }
}

package com.runningsnail.demos.activity.viewanimation.interceptor;

import android.view.animation.BounceInterpolator;

import com.runningsnail.demos.common.utils.HiLogger;

/**
 * @author yongjie created on 2018/12/9.
 */
public class MyBounceInterceptor extends BounceInterpolator {

    public static final String TAG = "MyBounceInterceptor";

    @Override
    public float getInterpolation(float t) {
        HiLogger.d(TAG, "t=" + t);
        return super.getInterpolation(t);
    }
}

package com.runningsnail.demos.common.utils;

import android.util.Log;
import android.view.View;

public class AnimUtil {
    private final static String TAG = "AnimUtil";
    /**
     * 缩放动画
     *
     * @param v 目标控件
     * @param hasFocus 是否聚焦
     */
    public static void scaleView(View v, boolean hasFocus) {
        try {
            if (v.getParent() != null) {
                v.getParent().bringChildToFront(v);
            } else {
                v.bringToFront();
            }
            v.animate().setDuration(100).scaleX(hasFocus ? 1.1f : 1.0f).scaleY(hasFocus ? 1.1f : 1.0f).start();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
}

package com.runningsnail.demos.activity.tv.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * @author yongjie created on 2019/1/28.
 */
public class CustomTextView extends TextView {

    public static final String TAG = "KeyEvent_CustomTextView";

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.d(TAG, "dispatchKeyEvent");
        return super.dispatchKeyEvent(event);
    }
}


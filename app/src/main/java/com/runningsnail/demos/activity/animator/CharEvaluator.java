package com.runningsnail.demos.activity.animator;

import android.animation.TypeEvaluator;

import com.runningsnail.demos.activity.animator.entity.MyCharacter;
import com.runningsnail.demos.common.utils.HiLog;

/**
 * @author yongjie created on 2019/1/6.
 */
public class CharEvaluator implements TypeEvaluator<MyCharacter> {

    public static final String TAG = "CharEvaluator";

    @Override
    public MyCharacter evaluate(float fraction, MyCharacter startValue,
                                MyCharacter endValue) {
        int start = startValue.ch;
        int end = endValue.ch;
        char resultChar = (char) (start + (end - start) * fraction);
        HiLog.d(TAG, "fraction:" + fraction + ",resultChar:" + resultChar);
        return new MyCharacter(resultChar);
    }
}

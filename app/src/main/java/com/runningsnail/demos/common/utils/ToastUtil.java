package com.runningsnail.demos.common.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @author yongjie on 2018/6/26.
 */
public class ToastUtil {

    private static Toast toast;

    public static void showToast(Context context, CharSequence charSequence) {
        if (toast == null) {
            toast = Toast.makeText(context, charSequence, Toast.LENGTH_SHORT);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(charSequence);
        toast.show();
    }


    public static void showToastBottom20(Context context, CharSequence charSequence) {
        if (toast == null) {
            toast = Toast.makeText(context, charSequence, Toast.LENGTH_SHORT);
        }
        toast.setGravity(Gravity.BOTTOM, 0, 20);
        toast.setText(charSequence);
        toast.show();
    }

    public static void showToastTop20(Context context, CharSequence charSequence) {
        if (toast == null) {
            toast = Toast.makeText(context, charSequence, Toast.LENGTH_SHORT);
        }
        toast.setGravity(Gravity.TOP, 0, 20);
        toast.setText(charSequence);
        toast.show();
    }
}

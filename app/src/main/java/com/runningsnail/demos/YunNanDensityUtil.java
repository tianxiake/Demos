package com.runningsnail.demos;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;


/**
 * 一个用来获取屏幕各种参数的工具类
 */
public class YunNanDensityUtil {



    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (spValue * scale + 0.5f);
    }

    /**
     * 这个方法用来获取屏幕的宽度 比如 1080p 的宽度就是 1080
     * 单位 px
     */

    public static int getDisplayWidth(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int widthPixels = dm.widthPixels;
        return widthPixels;
    }

    /**
     * 这个方法用来获取屏幕高度 包括状态栏的高度  比如 1080p 的高度就 1920
     * 单位px
     *
     * @return
     */
    public static int getDisplayHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }


    //获取状态栏高度  单位是px 一般是24dp 1080p 是72px
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    /**
     * 屏幕方向是否是横向
     *
     * @param context
     * @return
     */
    public static boolean isScreenLand(Context context) {
        return YunNanDensityUtil.getDisplayWidth(context) > YunNanDensityUtil.getDisplayHeight(context);
    }



    /**
     * 计算该视图在全局坐标系中的x，y值，（注意这个值是要从屏幕顶端算起，也就是包括了通知栏的高度）//获取在当前屏幕内的绝对坐标
     * 这个方法必须等控件全部加载完成的时候才会生效
     *
     * @param view
     */

    public static int[] getLocationOnScreen(View view) {
        int[] location1 = new int[2];
        view.getLocationOnScreen(location1);
        return location1;
    }

    /**
     * 判断当前手机是否允许方向旋转
     */
    public static boolean isSystemAutoOrtation(Context context) {
        int systemRotation = -1;
        try {
            systemRotation = getScreenRotation(context.getContentResolver());
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            systemRotation = -1;
        } finally {
            return systemRotation == 1;
        }
    }

    private static int getScreenRotation(ContentResolver cr) throws Settings.SettingNotFoundException {
        return Settings.System.getInt(cr,
                Settings.System.ACCELEROMETER_ROTATION);
    }
}

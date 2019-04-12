package com.runningsnail.demos.common.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @author liuyongjie create on 2018/12/24.
 */
public class ImageLoader {

    public static final String TAG = "ImageLoader";

    public static void loadGif(ImageView imageView, int resId) {
        try {
            Glide.with(imageView.getContext()).asGif().load(resId).into(imageView);

        } catch (Exception e) {
            HiLogger.e(TAG, "load gif error", e);
        }
    }
}

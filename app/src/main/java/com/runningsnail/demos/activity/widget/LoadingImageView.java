package com.runningsnail.demos.activity.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

/**
 * @author yongjie created on 2019/1/2.
 */
public class LoadingImageView extends AppCompatImageView {

    public static final String TAG = "LoadingImageView";

    private int currentIndex;
    private int count = 3;
    private int top;
    private ValueAnimator valueAnimator;

    public LoadingImageView(Context context) {
        super(context);
        init();
    }

    public LoadingImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        valueAnimator = ValueAnimator.ofInt(0, 100,0);
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                HiLogger.d(TAG, "onAnimationUpdate");
                Integer value = (Integer) animation.getAnimatedValue();
                setTop(top - value);

            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                HiLogger.d(TAG, "onAnimationStart");
                setImageResource(R.drawable.pic_one);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                HiLogger.d(TAG, "onAnimationEnd");

            }

            @Override
            public void onAnimationCancel(Animator animation) {
                HiLogger.d(TAG, "onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                HiLogger.d(TAG, "onAnimationRepeat");
                currentIndex++;
                int extra = currentIndex % count;
                switch (extra) {
                    case 0:
                        setImageResource(R.drawable.pic_one);
                        break;
                    case 1:
                        setImageResource(R.drawable.pic_two);
                        break;
                    case 2:
                        setImageResource(R.drawable.pic_three);
                        break;
                    default:
                        break;
                }

            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        HiLogger.d(TAG, "onLayout");
        this.top = top;
    }
}

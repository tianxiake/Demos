package com.runningsnail.demos.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.ScaleAnimation;

import com.runningsnail.demos.utils.HiLog;

/**
 * @author yongjie created on 2018/12/9.
 */
public class CustomImageView extends AppCompatImageView implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    public static final String TAG = "CustomImageView";
    GestureDetector gestureDetector = new GestureDetector(this);

    private float fromX = 1f;
    private float toX;
    private float formY = 1f;
    private float toY;
    private float pivotX;
    private float pivotY;

    public CustomImageView(Context context) {
        this(context, null);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        gestureDetector.setOnDoubleTapListener(this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        HiLog.d(TAG, "onTouchEvent");
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        pivotX = e.getX();
        pivotY = e.getY();
        HiLog.d(TAG, "双击");
        if (formY > 2f && fromX > 2f) {
            //回到初始状态
            ScaleAnimation scaleAnimation = new ScaleAnimation(fromX, 1f, formY, 1f, pivotX, pivotY);
            scaleAnimation.setDuration(100);
            scaleAnimation.setFillAfter(true);
            this.startAnimation(scaleAnimation);
            formY = 1f;
            fromX = 1f;
        } else {
            toY = formY * 1.2f;
            toX = fromX * 1.2f;
            ScaleAnimation scaleAnimation = new ScaleAnimation(fromX, toX, formY, toY, pivotX, pivotY);
            scaleAnimation.setDuration(100);
            scaleAnimation.setFillAfter(true);
            this.startAnimation(scaleAnimation);
            formY = toY;
            fromX = toX;
        }
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}

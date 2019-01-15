package com.runningsnail.demos.activity.animator.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author yongjie created on 2019/1/15.
 */
public class GetSegmentView extends View {

    Path path;
    Paint paint;
    Path desPath;
    ValueAnimator valueAnimator;

    float stopDestance;
    PathMeasure pathMeasure;

    private boolean isInit = false;

    public GetSegmentView(Context context) {
        super(context);

    }

    public GetSegmentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GetSegmentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private void init() {
        //禁用硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        path = new Path();
        path.addCircle(getWidth() / 2, getHeight() / 2, 200, Path.Direction.CW);
        desPath = new Path();
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        pathMeasure = new PathMeasure(path, false);
        valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float animatedValue = (Float) animation.getAnimatedValue();
                stopDestance = animatedValue;
                //触发重绘制
                invalidate();
            }
        });
        valueAnimator.start();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (!isInit) {
            init();
            isInit = true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        float distance = pathMeasure.getLength() * stopDestance;
        desPath.reset();
        pathMeasure.getSegment(0, distance, desPath, true);

        canvas.drawPath(desPath, paint);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        valueAnimator.cancel();
    }
}

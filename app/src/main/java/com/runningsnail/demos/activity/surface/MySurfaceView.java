package com.runningsnail.demos.activity.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.runningsnail.demos.common.utils.DensityUtils;
import com.runningsnail.demos.common.utils.HiLogger;

/**
 * @author yongjie created on 2019-08-14.
 * 第一步：继承SurfaceView 实现SurfaceHolder.Callback
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private static final String TAG = "MySurfaceView";
    private SurfaceHolder mSurfaceHolder;
    //绘图的Canvas
    private Canvas mCanvas;
    //子线程标志位
    private boolean mIsDrawing;
    private Paint mPaint;
    private int time;

    public MySurfaceView(Context context) {
        this(context, null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(DensityUtils.dp2px(getContext(), 20));
        mPaint.setStrokeWidth(5);
        initView();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //第三步:surfaceView构建后启动新线程进行绘制行为
        mIsDrawing = true;
        new Thread(this).start();
        HiLogger.i(TAG, "surfaceCreated");
    }

    public void start(int time) {
        this.time = time;
    }

    public void restart(int time) {
        mIsDrawing = true;
        this.time = time;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        HiLogger.i(TAG, "format: %s width: %s height: %s", format, width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
        HiLogger.i(TAG, "surfaceDestroyed");
    }

    @Override
    public void run() {
        HiLogger.i(TAG, "run");
        while (mIsDrawing) {
            if (time == 0) {
                mIsDrawing = false;
                return;
            }
            drawTime(time);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time--;
        }
    }

    private void drawTime(int time) {
        HiLogger.i(TAG, "drawTime time:" + time);
        try {
            //第四步:通过SurfaceHolder获取Canvas进行绘制
            //获得canvas对象
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawText(time + "", this.getWidth() / 2, this.getHeight() / 2 + DensityUtils.dp2px(getContext(), 20) / 2, mPaint);

        } catch (Exception e) {

        } finally {
            //第五步:提交绘制的Canvas显示
            if (mCanvas != null) {
                //释放canvas对象并提交画布
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    /**
     * 初始化View
     */
    private void initView() {
        //第二步:注册callback并获取 SurfaceHolder
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        setFocusable(true);
        setKeepScreenOn(true);
        setFocusableInTouchMode(true);
    }
}

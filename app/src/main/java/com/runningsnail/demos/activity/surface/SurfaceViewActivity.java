package com.runningsnail.demos.activity.surface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * SurfaceView使用步骤
 * <p>
 * 1、自定义View继承自SurfaceView并实现SurfaceHolder.Callback
 * 2、初始化时在自定义的SurfaceView中注册SurfaceHolder.Callback
 * 3、当SurfaceView的surfaceCreated()方法触发后启动新线程使用Canvas去绘制图形
 * 4、mCanvas = mSurfaceHolder.lockCanvas(); 获取画布,画布绘制完成调用mSurfaceHolder.unlockCanvasAndPost(mCanvas);去提交绘制的内容
 * </p>
 */

public class SurfaceViewActivity extends AppCompatActivity {

    @BindView(R.id.msv_content)
    MySurfaceView msvContent;
    @BindView(R.id.btn_translate)
    Button btnTranslate;
    @BindView(R.id.fl_content)
    FrameLayout flContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);
        ButterKnife.bind(this);
        msvContent.start(10);


    }


    @OnClick({R.id.btn_translate})
    public void onClick(View view) {
        msvContent.setScaleX(msvContent.getScaleX() + 1);
        msvContent.setTranslationY(msvContent.getTranslationY() + 50);
        msvContent.restart(10);
    }
}

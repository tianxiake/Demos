package com.runningsnail.demos.activity.viewanimation;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 帧动画，这种方式其实使用动画的Drawable来实现的
 */
public class FrameAnimActivity extends AppCompatActivity {


    @BindView(R.id.iv_xml)
    ImageView ivXml;

    @BindView(R.id.iv_java)
    ImageView ivJava;

    @BindView(R.id.btn_xml)
    Button btnXml;

    @BindView(R.id.btn_java)
    Button btnJava;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_anim);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_java, R.id.btn_xml})
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {
            case R.id.btn_xml:
                ivXml.setVisibility(View.VISIBLE);
                ivJava.setVisibility(View.GONE);
                //xml是直接
                AnimationDrawable anim = (AnimationDrawable) ivXml.getDrawable();
                //设置反复播放
                anim.setOneShot(false);
                //启动动画
                anim.start();
                break;
            case R.id.btn_java:
                ivJava.setVisibility(View.VISIBLE);
                ivXml.setVisibility(View.GONE);
                //Java代码
                AnimationDrawable animationDrawable = new AnimationDrawable();
                animationDrawable.addFrame(getDrawable("big_white_one", "drawable", getPackageName()), 60);
                animationDrawable.addFrame(getDrawable("big_white_two", "drawable", getPackageName()), 60);
                animationDrawable.addFrame(getDrawable("big_white_three", "drawable", getPackageName()), 60);
                animationDrawable.addFrame(getDrawable("big_white_four", "drawable", getPackageName()), 60);
                animationDrawable.addFrame(getResources().getDrawable(R.drawable.big_white_five), 60);
                animationDrawable.addFrame(getResources().getDrawable(R.drawable.big_white_six), 60);
                animationDrawable.addFrame(getResources().getDrawable(R.drawable.big_white_seven), 60);
                animationDrawable.addFrame(getResources().getDrawable(R.drawable.big_white_eight), 60);

                ivJava.setImageDrawable(animationDrawable);
                animationDrawable.setOneShot(false);
                animationDrawable.start();
            default:
                break;
        }

    }


    public Drawable getDrawable(String name, String defType, String packageName) {
        //通过这种方法拿到资源对应的id
        int id = getResources().getIdentifier(name, defType, packageName);
        Drawable drawable = getResources().getDrawable(id);
        return drawable;
    }
}

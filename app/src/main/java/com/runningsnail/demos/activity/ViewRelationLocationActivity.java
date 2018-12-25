package com.runningsnail.demos.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.runningsnail.demos.common.utils.HiLog;
import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 测试View相关位置相关的参数
 */

public class ViewRelationLocationActivity extends AppCompatActivity {

    public static final String TAG = "ViewRelationLocationActivity";
    @BindView(R.id.btn_transation_x)
    Button transactionX;
    @BindView(R.id.iv_content)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_relation_location);

        ButterKnife.bind(this);

        transactionX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setTranslationX(100);
                imageView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int left = imageView.getLeft();
                        int top = imageView.getTop();
                        int right = imageView.getRight();
                        int bottom = imageView.getBottom();
                        float x = imageView.getX();
                        float y = imageView.getY();
                        float translationX = imageView.getTranslationX();
                        HiLog.d(TAG, "end left:" + left + ",top:" + top + ",right:" + right +
                                ",bottom:" + bottom + ",x:" + x + ",y:" + y + ",translationX:" + translationX);
                    }
                }, 500);
            }
        });

        imageView.post(new Runnable() {
            @Override
            public void run() {
                int left = imageView.getLeft();
                int top = imageView.getTop();
                int right = imageView.getRight();
                int bottom = imageView.getBottom();
                float x = imageView.getX();
                float y = imageView.getY();
                float translationX = imageView.getTranslationX();
                HiLog.d(TAG, "start left:" + left + ",top:" + top + ",right:" + right +
                        ",bottom:" + bottom + ",x:" + x + ",y:" + y + ",translationX:" + translationX);
            }
        });
    }
}

package com.runningsnail.demos.activity.view;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewLocationTestActivity extends AppCompatActivity {

    private static final String TAG = "ViewLocationTestActivity";
    @BindView(R.id.btn_test)
    Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_location_test);
        ButterKnife.bind(this);

        btnTest.post(new Runnable() {
            @Override
            public void run() {

                float translationX = btnTest.getTranslationX();
                int left = btnTest.getLeft();
                int right = btnTest.getRight();
                HiLogger.i(TAG, "first translationX:%s,left:%s,right:%s", translationX, left, right);
            }
        });
    }

    @OnClick({R.id.btn_test})
    public void onClick(View view) {

        btnTest.setTranslationX(30);
        btnTest.postDelayed(new Runnable() {
            @Override
            public void run() {
                float translationX = btnTest.getTranslationX();
                int left = btnTest.getLeft();
                int right = btnTest.getRight();
                HiLogger.i(TAG, "second translationX:%s,left:%s,right:%s", translationX, left, right);
            }
        },3000);

    }
}

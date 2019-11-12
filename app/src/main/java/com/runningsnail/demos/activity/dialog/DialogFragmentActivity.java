package com.runningsnail.demos.activity.dialog;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * DialogFragment实现Dialog对话框效果
 */
public class DialogFragmentActivity extends AppCompatActivity {

    @BindView(R.id.btn_show_dialog)
    Button button;

    @BindView(R.id.iv_content)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment);
        ButterKnife.bind(this);
        ImageLoader.loadGif(imageView, R.drawable.ic_normal_video_playing);
    }


    @OnClick(R.id.btn_show_dialog)
    public void onClick(View view) {
        showDialogFragment();
    }

    private void showDialogFragment() {
        new VideoIntroDialogFragment().show(getSupportFragmentManager(), "view");
    }
}

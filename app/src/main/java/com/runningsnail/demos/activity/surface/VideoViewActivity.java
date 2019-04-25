package com.runningsnail.demos.activity.surface;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoViewActivity extends AppCompatActivity {

    @BindView(R.id.btn_play)
    Button btnPlay;
    @BindView(R.id.video_view)
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.btn_play})
    public void onClick(View view) {

        videoView.setVideoURI(Uri.parse("rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov"));
        videoView.start();
//        videoView.setMediaController(new MediaController(this));
//        videoView.requestFocus();
    }
}

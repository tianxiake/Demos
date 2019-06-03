package com.runningsnail.demos.activity.surface;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoViewActivity extends AppCompatActivity {


    public static final String TAG = "VideoViewActivity";

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

//        videoView.setVideoURI(Uri.parse("http://10.100.1.227:5100/CMSBK/0000000001000000020000000000751598.ts"));
//        videoView.setVideoURI(Uri.parse("http://10.100.1.229:5100/CMSBK/0000000001000000020000000000590781.ts?AuthInfo=&domain=257"));
        //多剧集id
//        videoView.setVideoURI(Uri.parse("http://10.100.1.229:5100/CMSBK/0091030001000000030000000000280409.ts?AuthInfo=&domain=257"));
        //点播取串
//        videoView.setVideoURI(Uri.parse("http://10.100.1.229:5100/CMSBK/0000000001000000020000000000587061.ts?AuthInfo=&domain=257"));
        //多剧集取串
//        videoView.setVideoURI(Uri.parse("http://10.100.1.229:5100/CMSBK/0091030001000000030000000000280280.ts?AuthInfo=&domain=257"));
        //单播
//        videoView.setVideoURI(Uri.parse("http://10.100.1.227:5100/CMSBK/0100000001000000060000000000000321.m3u8?AuthInfo=&version=Unicom3.0&PlayType=live&CMSID=starcor&PhysicalContentID=0100000001000000060000000000000321&domain=264&ProtocolType=1"));
        //回看
//        videoView.setVideoURI(Uri.parse("http://10.100.1.229:5100/CMSBK/0100000001000000060000000000000321.m3u8?AuthInfo=&version=Unicom3.0&PlayType=tvod&CMSID=starcor&PhysicalContentID=0100000001000000060000000000000321&domain=264&ProtocolType=1&PlaySeek=20190509T095422Z-20190509T095432Z"));
//        videoView.setVideoURI(Uri.parse("http://10.100.1.227:5100/CMSBK/0100000001000000060000000000000321.m3u8?AuthInfo=&version=Unicom3.0&PlayType=tvod&CMSID=starcor&PhysicalContentID=0100000001000000060000000000000321&domain=264&ProtocolType=1&PlaySeek=20190521T095422Z-20190521T095432Z"));
        //时移测试
//        videoView.setVideoURI(Uri.parse("http://10.100.1.227:5100/CMSBK/0100000001000000060000000000000321.m3u8?AuthInfo=&version=Unicom3.0&PlayType=tstv&CMSID=starcor&PhysicalContentID=0100000001000000060000000000000321&domain=264&ProtocolType=1&PlaySeek=20190521T230000Z-"));

        //直播测试
        videoView.setVideoURI(Uri.parse("udp://224.168.68.39:6000"));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                HiLogger.d(TAG, "onPrepared");
            }
        });
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                HiLogger.d(TAG, "onInfo %s", what);
                return false;
            }
        });
        videoView.start();
//        videoView.setMediaController(new MediaController(this));
//        videoView.requestFocus();
    }
}

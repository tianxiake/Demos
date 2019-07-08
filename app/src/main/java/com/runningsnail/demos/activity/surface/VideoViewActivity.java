package com.runningsnail.demos.activity.surface;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import java.util.HashMap;

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

        HiLogger.i(TAG, "onCreate");

    }

    @OnClick({R.id.btn_play})
    public void onClick(View view) {

        HiLogger.i(TAG, "onClick");
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
//        videoView.setVideoURI(Uri.parse("http://120.87.4.88:33200/EPG/MediaService/RedirectPlay.jsp?UserToken=ttSp3riy0t68w9-StjzS8bvL4&UserName=test0414&CPID=nfcmiptv&ContentType=lookback&ContentID=10000100000000050000000000298705&MediaID=10000100000000060000000000337082&PlaySeek=20190702130000-&NPT=-X"));

        //南传联通回看
//        videoView.setVideoURI(Uri.parse("http://120.87.4.88:33200/EPG/MediaService/RedirectPlay.jsp?UserToken=ttSphv8k5e90gq-Stjzt19dcf&UserName=test0414&CPID=nfcmiptv&ContentType=lookback&ContentID=10000100000000050000000000298705&MediaID=10000100000000060000000000337082&PlaySeek=20190702000000-20190702001000&NPT=-X"));
        //CCTV1综合回看
//        videoView.setVideoURI(Uri.parse("http://120.87.4.88:33200/EPG/MediaService/RedirectPlay.jsp?UserToken=ttSphv8k5e90gq-Stjzt19dcf&UserName=test0414&CPID=nfcmiptv&ContentType=lookback&ContentID=10000100000000050000000000296940&MediaID=10000100000000060000000000334541&PlaySeek=20190702000000-20190702001000&NPT=-X"));
        //CCTV1综合时移
        videoView.setVideoURI(Uri.parse("http://120.87.19.109/PLTV/88888973/224/3221225745/10000100000000060000000000334569_0.smil/index.m3u8?rrsip=120.87.19.109&fmt=ts2hls&servicetype=1&icpid=&accounttype=1&limitflux=-1&limitdur=-1&GuardEncType=2&accountinfo=z9UzVYlAOZky97z%2BQKWBehMkCFOe1g%2FXRzzHWzOtDB6gq9eQIx5WXsFkvNKRFPxuLCXN5C2rq82X8BRUBv%2BgYwj8uQDmajmpUGDeMrFTF2GX7YvnjIzqtOT%2BP5as%2B3hx%3A20190702160931%2C11002000000032%2C218.107.60.6%2C20190702160931%2C10000100000000050000000000296970%2C7A1EB8B0AE2499A5AB221B83647F4635%2C-1%2C1%2C1%2C%2C%2C2%2C%2C%2C%2C2%2CEND"));
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
        HiLogger.i(TAG, "get first Frame");
//        Bitmap netVideoBitmap = getNetVideoBitmap("https://o6yh618n9.qnssl.com/grKyofaC_9428902051.mp4");
//        HiLogger.i(TAG, "netVideoBitmap:" + (netVideoBitmap == null ? "Null" : "获取成功"));

    }

//    public static Bitmap getNetVideoBitmap(String videoUrl) {
//        Bitmap bitmap = null;
//
//        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//        try {
//            //根据url获取缩略图
//            retriever.setDataSource(videoUrl, new HashMap());
//            retriever.
//                    //获得第一帧图片
//                    bitmap = retriever.getFrameAtTime();
//        } catch (IllegalArgumentException e) {
//            HiLogger.i(TAG, "error", e);
//        } finally {
//            retriever.release();
//        }
//        HiLogger.i(TAG, "bitmap");
//        return bitmap;
//    }
}


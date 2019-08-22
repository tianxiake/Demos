package com.runningsnail.demos.activity.surface;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
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

    String[] playUrl = new String[]{
            "http://111.7.146.91:33200/EPG/MediaService/RedirectPlay.jsp?userToken=123456789&ContentType=vod&MediaID=NanFangCMJCSSP02000201000000022019042399006207&ContentID=NanFangCMJCSSP02000201000000012019042399574357",
            "http://111.7.146.91:33200/EPG/MediaService/RedirectPlay.jsp?userToken=123456789&ContentType=vod&MediaID=NanFangCMJCSSP02000201000000022019042399006247&ContentID=NanFangCMJCSSP02000201000000012019042399874361",
            "http://111.7.146.91:33200/EPG/MediaService/RedirectPlay.jsp?userToken=123456789&ContentType=vod&MediaID=NanFangCMJCSSP02000201000000022019042399006207&ContentID=NanFangCMJCSSP02000201000000012019042399574357"
    };
    @BindView(R.id.btn_play_next)
    Button btnPlayNext;
    @BindView(R.id.fl_content)
    FrameLayout flContent;

    private int playIndex = 0;

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int currentPosition = videoView.getCurrentPosition();
            HiLogger.i(TAG, "currentPosition:" + currentPosition);
            this.sendEmptyMessageDelayed(1, 30);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ButterKnife.bind(this);

        HiLogger.i(TAG, "onCreate");
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                HiLogger.d(TAG, "onPrepared");
                videoView.seekTo(0);
            }
        });
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                HiLogger.d(TAG, "onInfo %s", what);
                return false;
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                HiLogger.i(TAG, "onCompletion");
                playNext(playIndex);
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                HiLogger.i(TAG, "onError what:%s extra:%s", what, extra);
                return false;
            }
        });

//        handler.sendEmptyMessageDelayed(1, 30);
    }

    //    String playUrl = "http://111.7.174.11/88889315/16/20190710/271128404/271128404.mp4?rrsip=111.7.174.11&icpid=&accounttype=1&limitflux=-1&limitdur=-1&accountinfo=:2019071209264";
//    String playUrl = "http://111.7.174.11/88889315/16/20190703/271066682/271066682.mp4?rrsip=111.7.174.11&icpid=&accounttype=1&limitflux=-1&limitdur=-1&accountinfo=:2019071210405";
    @OnClick({R.id.btn_play, R.id.btn_play_next})
    public void onClick(View view) {
        HiLogger.i(TAG, "onClick");
        int id = view.getId();
        switch (id) {
            case R.id.btn_play:
                videoView.setVideoURI(Uri.parse("rtmp://live.hkstv.hk.lxdns.com/live/hks"));
                videoView.start();
//                btnPlay.setEnabled(false);
//                playNext(playIndex);
                break;
            case R.id.btn_play_next:
                videoView.stopPlayback();
                flContent.removeView(videoView);
//                playNext(playIndex);
                break;
            default:
                break;
        }


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
//        videoView.setMediaController(new MediaController(this));
//        videoView.requestFocus();
//        HiLogger.i(TAG, "get first Frame");
//        Bitmap netVideoBitmap = getNetVideoBitmap("https://o6yh618n9.qnssl.com/grKyofaC_9428902051.mp4");
//        HiLogger.i(TAG, "netVideoBitmap:" + (netVideoBitmap == null ? "Null" : "获取成功"));

    }

    private void playNext(int index) {
        if (index > (playUrl.length - 1)) {
            index = 0;
        }
        HiLogger.i(TAG, "playIndex:" + index);
        videoView.setVideoURI(Uri.parse(playUrl[index]));
        videoView.start();
        playIndex++;
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


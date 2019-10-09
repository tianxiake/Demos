package com.runningsnail.demos.activity.surface;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import java.io.IOException;

public class MediaplayerAndSurfaceViewActivity extends AppCompatActivity {
    private static final String TAG = "MediaplayerAndSurfaceViewActivity";

    private String url = "http://vfx.mtime.cn/Video/2019/06/18/mp4/190618231303510938.mp4";

    SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaplayer_and_texture);
        surfaceView = findViewById(R.id.surfaceView);
        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceView.getHolder().setKeepScreenOn(true);
        surfaceView.getHolder().addCallback(new SurfaceListener());

    }


    private class SurfaceListener implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            HiLogger.i(TAG, "surfaceCreated");

            try {
                final MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.reset();
                mediaPlayer.setDisplay(holder);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mediaPlayer.start();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            HiLogger.i(TAG, "surfaceDestroyed");
        }
    }


    public void play() throws IOException {
//        mediaPlayer.reset();
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        mediaPlayer.setDataSource("http://vfx.mtime.cn/Video/2019/06/29/mp4/190629004821240734.mp4");
//        mediaPlayer.prepareAsync();
//        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mediaPlayer.start();
//                mediaPlayer.setDisplay(surfaceHolder);
//                mediaPlayer.setScreenOnWhilePlaying(true);
//            }
//        });


    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

package com.runningsnail.demos.activity.surface;

import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;
import android.view.TextureView;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MediaplayerAndTextureActivity extends AppCompatActivity {

    private static final String TAG = "MediaplayerAndTextureActivity";
    @BindView(R.id.tv_text_ure)
    TextureView textureView;
    private Surface mySurface;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaplayer_and_texture);
        ButterKnife.bind(this);

        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                HiLogger.i(TAG, "onSurfaceTextureAvailable");
                mySurface = new Surface(surface);
                play("http://vfx.mtime.cn/Video/2019/06/29/mp4/190629004821240734.mp4");
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                HiLogger.i(TAG, "onSurfaceTextureSizeChanged");
            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
                HiLogger.i(TAG, "onSurfaceTextureAvailable");
            }
        });


    }

    private void play(String url) {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setSurface(mySurface);
            mediaPlayer.setDataSource(url);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    HiLogger.i(TAG, "onPrepared");
                    mediaPlayer.start();
                }
            });
        } catch (Exception e) {
            HiLogger.e(TAG, "", e);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

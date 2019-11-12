package com.runningsnail.demos.activity.media;

import android.media.MediaPlayer;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import java.io.IOException;

public class AudioPlayActivity extends AppCompatActivity {

    private static final String TAG = "AudioPlayActivity";
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_play);
    }


    @Override
    protected void onResume() {
        super.onResume();
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    HiLogger.i(TAG, "onCompletion");
                }
            });
            mediaPlayer.setDataSource("/sdcard/wind.mp3");
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}

package com.runningsnail.demos.activity.surface;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;

import android.view.TextureView;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextureViewActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener {
    //每一帧数据的长度 3110400
    public static final String TAG = "TextureViewActivity";

    @BindView(R.id.tv_texture_view)
    TextureView textureView;

    private Camera mCamera;
    private FileOutputStream fileOutputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texture_view);
        ButterKnife.bind(this);

        textureView.setSurfaceTextureListener(this);
        try {
            fileOutputStream = new FileOutputStream("/sdcard/vidoe.data");
        } catch (FileNotFoundException e) {
            HiLogger.e(TAG,"",e);
        }
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        HiLogger.d(TAG, "onSurfaceTextureAvailable");
        int numberOfCameras = Camera.getNumberOfCameras();
        HiLogger.d(TAG, "numberOfCameras %s", numberOfCameras);
        //0表示前置 1表示后置
        mCamera = Camera.open(1);
        mCamera.setDisplayOrientation(90);
        try {
            mCamera.setPreviewTexture(surface);
            mCamera.setPreviewCallback(new Camera.PreviewCallback() {
                @Override
                public void onPreviewFrame(byte[] data, Camera camera) {
                    int previewFormat = mCamera.getParameters().getPreviewFormat();
                    HiLogger.d(TAG, "previewFormat: %s dataLength: %s", previewFormat, data.length);
                    try {
                        fileOutputStream.write(data);
                        HiLogger.d(TAG,"writeToFileSuccess");
                    } catch (IOException e) {
                        HiLogger.d(TAG,"writeToFileError");
                        HiLogger.e(TAG,"",e);
                    }
                }
            });
            mCamera.startPreview();
        } catch (IOException io) {
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        HiLogger.d(TAG, "onSurfaceTextureSizeChanged");
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        HiLogger.d(TAG, "onSurfaceTextureDestroyed");
        mCamera.stopPreview();
        mCamera.release();
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        HiLogger.d(TAG, "onSurfaceTextureUpdated");
    }
}

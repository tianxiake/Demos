package com.runningsnail.demos.activity.resources;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;
import com.runningsnail.demos.entity.Channel;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AssetActivity extends AppCompatActivity {

    private static final String TAG = "AssetActivity";
    @BindView(R.id.iv_content)
    ImageView ivContent;
    @BindView(R.id.btn_show)
    Button btnShow;
    @BindView(R.id.btn_assert_json)
    Button btnAssertJson;
    @BindView(R.id.btn_assert_dir)
    Button btnAssertDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.btn_show, R.id.btn_assert_json, R.id.btn_assert_dir})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
                openAssetResToShow();
                break;
            case R.id.btn_assert_json:
                readAssertJson();
                break;
            case R.id.btn_assert_dir:
                readAssertDir();
                break;
            default:
                break;
        }
    }

    private void readAssertDir() {
        try {
            AssetManager assets = this.getAssets();
            String[] pics = assets.list("res/pic");
            HiLogger.i(TAG, "res/pics: %s", Arrays.toString(pics));
            InputStream open = assets.open("res/pic/channel.json");
            Channel channel = new Gson().fromJson(new InputStreamReader(open), Channel.class);
            HiLogger.i(TAG, "res/pic/channel:%s", channel);
        } catch (Exception e) {
            HiLogger.e(TAG, "readAssertDir", e);
        }
    }

    private void readAssertJson() {
        try {
            AssetManager assets = this.getAssets();
            String[] pics = assets.list("pic");
            HiLogger.i(TAG, "pics: %s", Arrays.toString(pics));
            InputStream open = assets.open("pic/channel.json");
            Channel channel = new Gson().fromJson(new InputStreamReader(open), Channel.class);
            HiLogger.i(TAG, "channel:%s", channel);
        } catch (Exception e) {
            HiLogger.e(TAG, "readAssertJson", e);
        }
    }

    private void openAssetResToShow() {
        try {
            AssetManager assets = this.getAssets();
            //String[] list = assets.list("pic"); path 是相对assets目录下的相对路径，比如列举pic目录下的所有文件名直接写 pic 实际是./pic
            //String[] list = assets.list("pic/"); 错误写法,加/ 将无法得到pic路径下的所有文件列表
            InputStream open = assets.open("pic/Color.jpg", AssetManager.ACCESS_RANDOM);
            Bitmap bitmap = BitmapFactory.decodeStream(open);
            ivContent.setImageBitmap(bitmap);
            assets.close();
        } catch (Exception e) {
            HiLogger.e(TAG, "", e);
        }
    }
}

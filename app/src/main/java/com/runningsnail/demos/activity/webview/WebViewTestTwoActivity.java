package com.runningsnail.demos.activity.webview;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebViewTestTwoActivity extends AppCompatActivity {

    @BindView(R.id.btn_load_web)
    Button btnLoadWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_test_two);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_load_web})
    public void onClick(View view){

        openWeb();
    }

    private void openWeb() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("cn.gd.snm.station",
                "cn.gd.snm.station.activity.LauncherActivity"));
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        //传递的web页面地址
        intent.putExtra("webUrl", "http://hnfs.voole.com:8888/?act=topic&column=8215&auth=1&page=1&pagesize=5");
        startActivity(intent);
    }
}

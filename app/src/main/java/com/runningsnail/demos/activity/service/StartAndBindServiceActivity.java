package com.runningsnail.demos.activity.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;
import com.runningsnail.demos.common.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartAndBindServiceActivity extends AppCompatActivity {

    public static final String TAG = "StartAndBindServiceActivity";
    @BindView(R.id.btn_start_service)
    Button startServiceBtn;
    @BindView(R.id.btn_stop_service)
    Button stopServiceBtn;
    @BindView(R.id.btn_bind_service)
    Button bindServiceBtn;
    @BindView(R.id.btn_unbind_service)
    Button unbindServiceBtn;
    @BindView(R.id.btn_foreground_service)
    Button foregroundServiceBtn;

    private ServiceConnection serviceConnection;

    private MyService.MyBinder myBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service);
        ButterKnife.bind(this);
        serviceConnection = new MyServiceConnection();
    }


    @OnClick({R.id.btn_start_service, R.id.btn_stop_service,
            R.id.btn_bind_service, R.id.btn_unbind_service,
            R.id.btn_foreground_service})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_start_service:
                testStartService();
                break;
            case R.id.btn_stop_service:
                testStopService();
                break;
            case R.id.btn_bind_service:
                testBindService();
                break;
            case R.id.btn_unbind_service:
                testUnbindService();
                break;
            case R.id.btn_foreground_service:
                testForegroundService();
                break;
            default:
                break;
        }
    }

    private void testForegroundService() {
        Intent intent = new Intent(this, ForegroundService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        ToastUtil.showToast(this, "前台服务成功");
    }

    private void testUnbindService() {
        try {
            HiLogger.d(TAG, "serviceConnection: %s", serviceConnection);
            unbindService(serviceConnection);
            ToastUtil.showToast(this, "unbind服务成功");
        } catch (Exception e) {
            HiLogger.e(TAG, "", e);
        }
    }

    private void testBindService() {
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        ToastUtil.showToast(this, "bind服务成功");
    }


    private void testStopService() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
        ToastUtil.showToast(this, "stop服务成功");
    }

    private void testStartService() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
        ToastUtil.showToast(this, "start服务成功");
    }

    private class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            HiLogger.d(TAG, "onServiceConnected %s %s", name, service);
            myBinder = (MyService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            HiLogger.d(TAG, "onServiceDisconnected %s", name);
        }

        @Override
        public void onBindingDied(ComponentName name) {
            HiLogger.d(TAG, "onBindingDied %s", name);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        testUnbindService();
    }
}

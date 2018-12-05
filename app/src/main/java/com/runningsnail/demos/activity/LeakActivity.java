package com.runningsnail.demos.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.runningsnail.demos.interfaces.CallBack;
import com.runningsnail.demos.TestNet;
import com.runningsnail.demos.R;

public class LeakActivity extends AppCompatActivity {

    public static final String TAG = "LeakActivity";
    private static final Object obj = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);

//        //内存泄漏代码：
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                synchronized (obj) {
//                    try {
//                        obj.wait();
//                    } catch (Exception e) {
//
//                    }
//                }
//            }
//        }.start();

        //内存泄露代码 callback会持有外部类的应用
        TestNet testNet = new TestNet();
        testNet.doGet(new CallBack() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "onSuccess");
//                try {
//                    while (true) {
//                        Log.d(TAG, "onSuccess");
//                        Thread.sleep(1000);
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onFail() {
                Log.d(TAG, "onFail");
//                loginFail();
            }
        });
    }

    public void loginSuccess() {
        Log.d(TAG, "login success");
    }

    public void loginFail() {
        Log.d(TAG, "login fail");
    }
}

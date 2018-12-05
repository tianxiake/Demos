package com.runningsnail.demos.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.runningsnail.demos.R;

public class LeakActivity extends AppCompatActivity {

    private static final Object obj = new Object();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);

        //内存泄漏代码：
        new Thread(){
            @Override
            public void run() {
                super.run();
                synchronized (obj){
                    try{
                    obj.wait();
                }catch (Exception e){

                }
            }
            }
        }.start();

    }
}

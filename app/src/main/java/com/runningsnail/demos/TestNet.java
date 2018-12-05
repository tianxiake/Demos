package com.runningsnail.demos;

import com.runningsnail.demos.interfaces.CallBack;

/**
 * @author liuyongjie create on 2018/12/5.
 */
public class TestNet {

    public void doGet(final CallBack callBack) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                    callBack.onSuccess();
                } catch (InterruptedException e) {
                    callBack.onFail();
                    e.printStackTrace();
                }
            }
        }.start();
    }
}

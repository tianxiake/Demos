package com.runningsnail.javalib.communication;

/**
 * @author yongjie created on 2019-06-18.
 * 消费者
 */
public class Consumer extends Thread {

    public Consumer(String s) {
        super(s);
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                synchronized (Productor.messages) {
                    if (Productor.messages.isEmpty()) {
                        Productor.messages.wait();
                    }
                    Message pop = Productor.messages.pop();
                    System.out.println(Thread.currentThread().getName() + "消费一个产品");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

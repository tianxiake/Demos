package com.runningsnail.javalib.communication;

import java.util.LinkedList;

/**
 * @author yongjie created on 2019-06-18.
 * 生产者
 */
public class Productor extends Thread {

    public static LinkedList<Message> messages = new LinkedList<>();

    public Productor(String s) {
        super(s);
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                synchronized (messages) {
                    if (messages.size() == 1000) {
                        Thread.sleep(1000);
                    }
                    Message message = new Message();
                    messages.add(message);
                    System.out.println("生产一个Message");
                    messages.notify();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

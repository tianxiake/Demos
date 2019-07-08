package com.runningsnail.javalib.communication;

/**
 * @author yongjie created on 2019-06-18.
 */
public class Test {
    public static void main(String[] args) {


        new Consumer("Consumer_1").start();
        new Consumer("Consumer_2").start();
        new Consumer("Consumer_3").start();

        new Productor("Productor").start();
    }

}

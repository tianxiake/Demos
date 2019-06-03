package com.runningsnail.demos.pattern.factory;

/**
 * @author yongjie created on 2019-05-22.
 */
public class CircleShape implements Shape {
    @Override
    public void draw() {
        System.out.println("绘制圆形");
    }
}

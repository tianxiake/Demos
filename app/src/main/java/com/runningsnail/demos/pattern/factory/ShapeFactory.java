package com.runningsnail.demos.pattern.factory;

/**
 * @author yongjie created on 2019-05-22.
 */
public class ShapeFactory {


    public static Shape getShape(String flag) {
        Shape shape = null;
        switch (flag) {
            case "circle":
                shape = new CircleShape();
                break;
            case "rectangle":
                shape = new RectangleShape();
                break;
            default:
                System.out.println("没有这个" + flag + "的产品");
                break;
        }
        return shape;
    }

}

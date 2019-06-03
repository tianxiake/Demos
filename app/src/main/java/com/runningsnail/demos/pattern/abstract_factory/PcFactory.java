package com.runningsnail.demos.pattern.abstract_factory;

/**
 * @author yongjie created on 2019-05-22.
 */
public class PcFactory {

    public static Mouse getMouse(String flag) {
        switch (flag) {
            case "hp":

                break;
            case "mi":
                break;
            default:
                break;
        }

        return null;
    }


    public static Keyboard getKeyboard(String flag) {
        return null;
    }
}

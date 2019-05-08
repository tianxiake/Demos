package com.runningsnail.javalib.genericity;

/**
 * @author yongjie created on 2019-05-07.
 */
public class GeneratorMethod {

    /**
     * 返回值的泛型定义
     */
    public <T> T getKey(T value) {
        return value;
    }

    /**
     * 无返回值的泛型定义
     */
    public <T> void getKey2(T value) {
        System.out.println(value);
    }
}

package com.runningsnail.javalib.genericity;

/**
 * @author yongjie created on 2019-05-07.
 */
public class GeneratorImpl implements Generator<String> {

    @Override
    public String next() {
        return null;
    }

    public <T> T getValue(T key) {
        return key;
    }
}

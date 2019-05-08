package com.runningsnail.javalib.genericity;

/**
 * @author yongjie created on 2019-05-07.
 */
public class GenericOne<T extends Number> {


    T key;

    public GenericOne(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

}

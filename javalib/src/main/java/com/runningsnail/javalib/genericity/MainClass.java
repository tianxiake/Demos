package com.runningsnail.javalib.genericity;

import com.runningsnail.javalib.genericity.GenericOne;

public class MainClass {

    public static void main(String[] args) {
//        GenericOne<Integer> objectGenericOne = new GenericOne<Integer>(1111);
//        GenericOne<String> stringGenericOne = new GenericOne<String>("1111");
//        System.out.println(objectGenericOne.getKey());
//        System.out.println(stringGenericOne.getKey());
//
//        System.out.println(objectGenericOne.getClass().equals(stringGenericOne.getClass()));
//
//
//        GeneratorImpl generator = new GeneratorImpl();
//        Integer value = generator.getValue(1111);
//        System.out.println(value);


//        GeneratorMethod generatorMethod = new GeneratorMethod();
//        generatorMethod.getKey2(111);

        GenericOne genericOne = new GenericOne(1111);
        Object key = genericOne.getKey();
        System.out.println(key);
    }

    public static void showKeyValue1(GenericOne<? extends Number> obj) {
    }
}

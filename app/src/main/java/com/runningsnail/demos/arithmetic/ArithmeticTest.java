package com.runningsnail.demos.arithmetic;

import java.util.Stack;

/**
 * @author yongjie created on 2019-06-15.
 */
public class ArithmeticTest {

    public static void main(String[] args) {

        boolean vaildOne = isInvaildSymbol("[{()}]");
        boolean vaildTwo = isInvaildSymbol("[{()}]}");
        boolean vaildThree = isInvaildSymbol("][{()}]}");
        System.out.println(vaildOne);
        System.out.println(vaildTwo);
        System.out.println(vaildThree);
    }


    /**
     * 查找符号是否成对出现 比如三对符号 {}、[]、（）是合法符号
     * [{()}]
     */
    public static boolean isInvaildSymbol(String symbol) {
        if (symbol == null || symbol.trim().equals("") || symbol.length() % 2 != 0) {
            return false;
        }
        Stack<String> sysbolStack = new Stack<>();
        int length = symbol.length();
        for (int i = 0; i < length; i++) {
            char c = symbol.charAt(i);
            if (c == '[' || c == '{' || c == '(') {
                sysbolStack.push(c + "");
            } else {
                //在一半以前的位置发现一个非左括号的符号一定是非法符号
                if (i < symbol.length() / 2) {
                    return false;
                }
                String pop = sysbolStack.pop();
                switch (pop) {
                    case ")":
                        if (!"(".equals(c + "")) {
                            return false;
                        }
                        break;
                    case "}":
                        if (!"{".equals(c + "")) {
                            return false;
                        }
                        break;
                    case "]":
                        if (!"[".equals(c + "")) {
                            return false;
                        }
                        break;
                }

            }
        }
        return true;
    }
}

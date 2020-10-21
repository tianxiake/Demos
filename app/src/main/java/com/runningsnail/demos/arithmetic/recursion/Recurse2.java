package com.runningsnail.demos.arithmetic.recursion;

/**
 * 阶乘问题
 * 求n!
 *
 * @author yongjie created on 2020/10/1.
 */
public class Recurse2 {

	public static void main(String[] args) {
		Recurse2 recursion = new Recurse2();
		int factorial = recursion.factorial(6);
		System.out.println(factorial);

	}

	/**
	 * 阶乘问题 3！ = 1x2x3
	 * 递推公式： f(n) = n * f(n-1)
	 * 终止条件： f(1) = 1
	 */
	public int factorial(int n) {
		if (n == 1) {
			return 1;
		}
		return n * factorial(n - 1);
	}
}

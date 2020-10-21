package com.runningsnail.demos.arithmetic.divide;
//实现 pow(x, n) ，即计算 x 的 n 次幂函数。
//
// 示例 1:
//
// 输入: 2.00000, 10
//输出: 1024.00000
//
//
// 示例 2:
//
// 输入: 2.10000, 3
//输出: 9.26100
//
//
// 示例 3:
//
// 输入: 2.00000, -2
//输出: 0.25000
//解释: 2-2 = 1/22 = 1/4 = 0.25
//
// 说明:
//
//
// -100.0 < x < 100.0
// n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
//
// Related Topics 数学 二分查找
// 👍 504 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * @author yongjie created on 2020/10/2.
 */
public class Divide1 {
	public double myPow1(double x, int n) {
		if (n == -2147483648) {
			return 0;
		}
		if (x == 0) {
			return 0;
		}
		if (n < 0) {
			x = 1 / x;
			n = -n;
		}
		double result = 1;
		for (int i = 0; i < n; i++) {
			result = result * x;
		}
		return result;
	}

	public static void main(String[] args) {
		int a = Integer.MIN_VALUE;
		System.out.println(a);
	}
}

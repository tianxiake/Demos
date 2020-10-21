package com.runningsnail.demos.arithmetic.recursion;

import java.util.ArrayList;
import java.util.List;

//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
//
//
// 示例：
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
//
// Related Topics 字符串 回溯算法
// 👍 1337 👎 0

/**
 * 括号生成 问题
 * https://leetcode-cn.com/problems/generate-parentheses/
 *
 * @author yongjie created on 2020/10/1.
 */
public class Recurse1 {
	public static void main(String[] args) {
		Recurse1 recurse1 = new Recurse1();
		List<String> result = recurse1.method1(3);
		System.out.println(result);

		List<String> result2 = recurse1.method1(3);
		System.out.println(result2);
	}

	/**
	 * 暴力求解方式
	 */
	public List<String> method1(int n) {
		List<String> results = new ArrayList<>();
		recurse(new char[2 * n], 0, results);
		return results;
	}

	/**
	 * 方法1的升级版本，方法1的缺点是有很多无效的情况都进行了计算。
	 * 这些无效的计算在计算的过程中就可以去掉。
	 * 方法2的核心思想是：
	 * 1、在放置的过程中，左括号的数量要小于n
	 * 2、右括号的数量要小于左括号
	 *
	 * @param n
	 * @return
	 */
	public List<String> method2(int n) {
		List<String> result = new ArrayList<>();
		StringBuilder stringBuilder = new StringBuilder();
		recurse(result, stringBuilder, 0, 0, n);
		return result;
	}

	private void recurse(List<String> result, StringBuilder stringBuilder, int left, int right, int n) {
		//终止条件
		if (stringBuilder.length() == n * 2) {
			result.add(stringBuilder.toString());
			return;
		}
		//处理逻辑
		if (left < n) {
			//往下传递
			stringBuilder.append("(");
			recurse(result, stringBuilder, left + 1, right, n);
			//重置状态
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		}

		if (right < left) {
			//往下传递
			stringBuilder.append(")");
			recurse(result, stringBuilder, left, right + 1, n);
			//重置状态
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		}
	}

	private void recurse(char[] current, int pos, List<String> results) {
		//终止条件
		if (pos == current.length) {
			if (valid(current)) {
				results.add(new String(current));
			}
		} else {
			//处理逻辑
			current[pos] = '(';
			//往下层传递
			recurse(current, pos + 1, results);
			current[pos] = ')';
			recurse(current, pos + 1, results);
			//重置状态释放资源
		}
	}

	private boolean valid(char[] chars) {
		int balance = 0;
		for (char currentChar : chars) {
			if (currentChar == '(') {
				balance++;
			} else {
				balance--;
			}
			if (balance < 0) {
				return false;
			}
		}
		return balance == 0;
	}

}

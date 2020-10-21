package com.runningsnail.demos.arithmetic.backtrack;
//给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
//
// 说明：解集不能包含重复的子集。
//
// 示例:
//
// 输入: nums = [1,2,3]
//输出:
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//]
// Related Topics 位运算 数组 回溯算法
// 👍 821 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.List;

/**
 * @author yongjie created on 2020/10/2.
 */
public class BackTrack1 {
	public static void main(String[] args) {
		BackTrack1 backTrack1 = new BackTrack1();
		List<List<Integer>> subsets = backTrack1.subsets(new int[]{1, 2, 3});
		System.out.println(subsets);
	}

	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList();
		backtrack(result, new ArrayList(), 0, nums);
		return result;
	}

	private void backtrack(List<List<Integer>> result, List<Integer> arrayList, int start, int[] nums) {
		result.add(new ArrayList<>(arrayList));
		for (int i = start; i < nums.length; i++) {
			arrayList.add(nums[i]);
			backtrack(result, arrayList, i + 1, nums);
			arrayList.remove(arrayList.size() - 1);
		}
	}
}

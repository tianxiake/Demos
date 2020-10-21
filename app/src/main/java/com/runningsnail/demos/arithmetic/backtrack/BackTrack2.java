package com.runningsnail.demos.arithmetic.backtrack;

//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
//
//
//
// 示例 1:
//
// 输入: [3,2,3]
//输出: 3
//
// 示例 2:
//
// 输入: [2,2,1,1,1,2,2]
//输出: 2
//
// Related Topics 位运算 数组 分治算法
// 👍 752 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.HashMap;
import java.util.Set;

/**
 * @author yongjie created on 2020/10/2.
 */
class BackTrack2 {

	public int majorityElement(int[] nums) {
		if (nums == null || nums.length <= 0) {
			return -1;
		}
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			Integer integer = map.get(nums[i]);
			if (integer == null) {
				map.put(nums[i], 1);
			} else {
				map.put(nums[i], ++integer);
			}
		}
		int max = nums.length / 2;
		int value = -1;
		Set<Integer> integers = map.keySet();
		for (Integer integer : integers) {
			Integer mapValue = map.get(integer);
			if (mapValue > max) {
				max = mapValue;
				value = integer;
			}
		}
		return value;
	}

}

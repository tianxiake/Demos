package com.runningsnail.demos.arithmetic.recursion;
//翻转一棵二叉树。
//
// 示例：
//
// 输入：
//
//      4
//   /   \
//  2     7
// / \   / \
//1   3 6   9
//
// 输出：
//
//      4
//   /   \
//  7     2
// / \   / \
//9   6 3   1
//
// 备注:
//这个问题是受到 Max Howell 的 原问题 启发的 ：
//
// 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
// Related Topics 树
// 👍 641 👎 0
//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class Recurse3 {

	public static void main(String[] args) {

	}

	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode leftNode = invertTree(root.left);
		TreeNode rightNode = invertTree(root.right);
		root.left = rightNode;
		root.right = leftNode;
		return root;
	}

	public TreeNode invertTree2(TreeNode root) {
		recurse(root);
		return root;
	}


	private void recurse(TreeNode root) {
		//终止条件
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {
			return;
		}
		//处理逻辑
		TreeNode left = root.left;
		TreeNode right = root.right;
		root.left = right;
		root.right = left;
		//往下层传递
		invertTree(left);
		invertTree(right);
		//重置状态
	}

	private class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}

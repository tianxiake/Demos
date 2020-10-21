package com.runningsnail.demos.arithmetic.tree;

/**
 * @author yongjie created on 2020/9/27.
 */
public class TreeTraverse {

	/**
	 * 前序遍历 根左右
	 *
	 * @param rootNode
	 */
	public void preOrderTraverse(TreeNode rootNode) {
		if (rootNode == null) {
			return;
		}
		System.out.println(rootNode.value);
		preOrderTraverse(rootNode.leftNode);
		preOrderTraverse(rootNode.rightNode);
	}

	/**
	 * 中序遍历 左根右
	 *
	 * @param rootNode
	 */
	public void inOrderTraverse(TreeNode rootNode) {
		if (rootNode == null) {
			return;
		}
		inOrderTraverse(rootNode.leftNode);
		System.out.println(rootNode.value);
		inOrderTraverse(rootNode.rightNode);
	}

	/**
	 * 后序遍历 左右根
	 *
	 * @param rootNode
	 */
	public void postOrderTraverse(TreeNode rootNode) {
		if (rootNode == null) {
			return;
		}
		postOrderTraverse(rootNode.leftNode);
		postOrderTraverse(rootNode.rightNode);
		System.out.println(rootNode.value);
	}


	public static class TreeNode {
		public int value;
		public TreeNode leftNode;
		public TreeNode rightNode;
	}
}

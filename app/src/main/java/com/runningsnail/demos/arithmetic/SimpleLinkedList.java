package com.runningsnail.demos.arithmetic;

import org.w3c.dom.Node;

/**
 * 单项链表
 *
 * @author yongjie created on 2020-02-15.
 */
public class SimpleLinkedList<T> {

	private int size = 0;
	private Node headNode;
	private Node tailNode;

	public SimpleLinkedList() {
	}

	public void add(T data) {
		Node node = new Node(data, null);
		if (headNode == null) {
			headNode = node;
		} else {
			tailNode.nextNode = node;
		}
		tailNode = node;
		size++;
	}

	public boolean isEmpty() {
		return size <= 0;
	}

	public boolean insert(T node, int position) {
		return false;
	}

	public boolean delete(T data) {
		Node preNode = headNode;
		for (Node node = headNode; node != null; node = node.nextNode) {
			if (node.data.equals(data)) {

				return true;
			}
		}
		return false;
	}

	public boolean delete(int position) {
		return false;
	}


	private class Node {
		T data;
		Node nextNode;

		public Node(T data, Node nextNode) {
			this.data = data;
			this.nextNode = nextNode;
		}
	}
}

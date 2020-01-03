package com.runningsnail.demos.pattern.response_chain;

import android.view.FocusFinder;

/**
 * @author yongjie created on 2019-12-26.
 */
public class Client {

	public static void main(String[] args) {
//		GroupLeader groupLeader = new GroupLeader();
//		Director director = new Director();
		Manager manager = new Manager();
		Boss boss = new Boss();

//		groupLeader.nextLeader = director;
//		director.nextLeader = manager;
		manager.nextLeader = boss;


	}
}

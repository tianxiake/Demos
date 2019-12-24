package com.runningsnail.demos.activity.arc.jetpack.databinding;

/**
 * @author yongjie created on 2019-11-15.
 */
public class MyTask {

	private String taskName;

	public MyTask(String taskName) {
		this.taskName = taskName;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("MyTask{");
		sb.append("taskName='").append(taskName).append('\'');
		sb.append('}');
		return sb.toString();
	}
}

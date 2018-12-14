package com.runningsnail.demos;

/**
 * @author liuyongjie create on 2018/12/13.
 */
public class RecommendBean {

	/**
	 * 背景图url
	 */
	public String icon;
	/**
	 * 标题
	 */
	public String title;
	/**
	 * 子标题
	 */
	public String subTitle;

	@Override
	public String toString() {
		return "RecommendBean{" +
				"icon='" + icon + '\'' +
				", title='" + title + '\'' +
				", subTitle='" + subTitle + '\'' +
				'}';
	}
}

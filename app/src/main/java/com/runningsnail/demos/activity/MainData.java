package com.runningsnail.demos.activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yongjie created on 2020-01-02.
 */
public class MainData {

	private List<ContentBean> content = new ArrayList<>();

	public List<ContentBean> getContent() {
		return content;
	}

	public void setContent(List<ContentBean> content) {
		this.content = content;
	}

	public static class ContentBean {
		/**
		 * title : ===TV开发===
		 * sub : [{"subTitle":"KeyEvent测试","path":"com.runningsnail.demos.activity.tv.KeyEventTestActivity"},{"subTitle":"阴影效果实现方案","path":"com.runningsnail.demos.activity.tv.ShadowEffectActivity"},{"subTitle":"测试本地命令","path":"com.runningsnail.demos.activity.tv.RuntimeExecuteActivity"},{"subTitle":"模拟三方应用","path":"com.runningsnail.demos.activity.tv.MockActivity"},{"subTitle":"有趣的属性","path":"com.runningsnail.demos.activity.tv.FunnyPropertyActivity"}]
		 */

		private String title;
		private List<SubBean> sub = new ArrayList<>();

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public List<SubBean> getSub() {
			return sub;
		}

		public void setSub(List<SubBean> sub) {
			this.sub = sub;
		}

		public static class SubBean {
			/**
			 * subTitle : KeyEvent测试
			 * path : com.runningsnail.demos.activity.tv.KeyEventTestActivity
			 */

			private String subTitle;
			private String path;

			public SubBean(String subTitle, String path) {
				this.subTitle = subTitle;
				this.path = path;
			}

			public String getSubTitle() {
				return subTitle;
			}

			public void setSubTitle(String subTitle) {
				this.subTitle = subTitle;
			}

			public String getPath() {
				return path;
			}

			public void setPath(String path) {
				this.path = path;
			}
		}
	}
}

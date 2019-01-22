package com.runningsnail.demos.common.utils;

import android.graphics.Rect;
import android.view.View;

public class ViewUtils {

	/**
	 * 判断控件是否被遮住，显示不全
	 *
	 * @param view 要判断的控件
	 * @return 如果被遮挡则返回true，否则返回false
	 */
	public static boolean isCover(View view) {
		if (view.getParent() == null) {
			return false;
		}
		Rect rect = new Rect();
		if (view.getGlobalVisibleRect(rect)) {
			if (rect.width() >= view.getMeasuredWidth() && rect.height() >= view.getMeasuredHeight()) {
				return false;
			}
		}
		return true;
	}

}

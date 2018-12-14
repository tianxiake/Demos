package com.runningsnail.demos;

import android.view.View;

/**
 * @author liuyongjie create on 2018/12/11.
 */
public interface OnItemClickListener<T> {

	void onItemClick(View view, T data, int position);
}

package com.runningsnail.demos;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author liuyongjie create on 2018/12/20.
 */

public class SimpleSpaceItemDecoration extends RecyclerView.ItemDecoration {
	private int leftSpace;
	private int topSpace;
	private int rightSpace;
	private int bottomSpace;

	public SimpleSpaceItemDecoration(int leftSpace, int topSpace, int rightSpace, int bottomSpace) {
		this.leftSpace = leftSpace;
		this.topSpace = topSpace;
		this.rightSpace = rightSpace;
		this.bottomSpace = bottomSpace;
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		super.getItemOffsets(outRect, view, parent, state);
		outRect.bottom = bottomSpace;
		outRect.left = leftSpace;
		outRect.right = rightSpace;
		outRect.top=topSpace;
	}
}




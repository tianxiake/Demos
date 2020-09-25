package com.runningsnail.demos.widget.tv;

import android.content.Context;
import android.util.AttributeSet;

/**
 * @author yongjie created on 2020/9/10.
 */
public class TestOneSelectionView extends TestSelectionView {


	public TestOneSelectionView(Context context) {
		super(context);
	}

	public TestOneSelectionView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TestOneSelectionView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	public void showUnfoldView() {
		super.showUnfoldView();
		contentView.getChildAt(0).requestFocus();
	}
}

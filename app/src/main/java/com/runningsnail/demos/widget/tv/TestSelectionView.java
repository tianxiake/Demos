package com.runningsnail.demos.widget.tv;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yongjie created on 2020/9/10.
 */

public class TestSelectionView extends SelectionsView<SimpleDataLoader> {
	private static final String TAG = "TestSelectionView";
	public static final String TEST = "test";
	@BindView(R.id.tv_title)
	TextView tvTitle;
	@BindView(R.id.content_view)
	LinearLayout contentView;
	private SimpleDataLoader simpleDataLoader;

	public TestSelectionView(Context context) {
		super(context);
	}

	public TestSelectionView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TestSelectionView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	public void initView() {
		LayoutInflater.from(this.getContext()).inflate(R.layout.test_selection_layout, this, true);
		ButterKnife.bind(this);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {

		return super.dispatchKeyEvent(event);
	}

	@Override
	public void setTitleText(String text) {
		tvTitle.setText(text);
	}

	@Override
	public void bindData(SimpleDataLoader simpleDataLoader) {
		this.simpleDataLoader = simpleDataLoader;
	}

	@Override
	public void showFoldView() {
		tvTitle.setTextSize(25);
		tvTitle.setTextColor(Color.WHITE);
		contentView.setVisibility(View.GONE);
	}

	@Override
	public void showUnfoldView() {
		tvTitle.setTextSize(32);
		tvTitle.setTextColor(Color.BLUE);
		contentView.setVisibility(View.VISIBLE);
		if (simpleDataLoader != null) {
			String title = simpleDataLoader.getTitle();
			HiLogger.d(TAG, "showUnfoldView: %s", title);
		}
		contentView.getChildAt(2).requestFocus();
	}
}

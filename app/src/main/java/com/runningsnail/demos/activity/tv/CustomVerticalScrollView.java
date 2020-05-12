package com.runningsnail.demos.activity.tv;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.runningsnail.demos.R;

import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yongjie created on 2020/4/27.
 */
public class CustomVerticalScrollView extends LinearLayout {
	@BindView(R.id.tv_content)
	TextView tvContent;
	@BindView(R.id.s)
	ScrollView s;
	@BindView(R.id.iv_indicator)
	ImageView ivIndicator;
	@BindView(R.id.fl_indicator_bg)
	FrameLayout flIndicatorBg;

	private int scrollDrawableOriginHeight;
	private int scrollDistance;

	public CustomVerticalScrollView(Context context) {
		this(context, null);
	}

	public CustomVerticalScrollView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomVerticalScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.setOrientation(HORIZONTAL);
		initView();
	}

	private void initView() {
		LayoutInflater.from(this.getContext()).inflate(R.layout.custom_scroll_text, this, true);
		ButterKnife.bind(this);
	}

	public void bindData(String textView){

	}
}

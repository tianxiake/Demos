package com.runningsnail.demos.widget.tv;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 选集区域父类
 *
 * @author yongjie created on 2020/9/10.
 */
public abstract class SelectionsView<T> extends FrameLayout {
	private static final String TAG = "SelectionsView";

	private String type = "";

	public SelectionsView(@NonNull Context context) {
		super(context);
		initView();
	}

	public SelectionsView(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public SelectionsView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}


	public abstract void initView();

	public abstract void setTitleText(String text);

	public abstract void bindData(T t);

	public abstract void showFoldView();

	public abstract void showUnfoldView();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}

package com.runningsnail.demos.activity.tv;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

/**
 * @author yongjie created on 2020/11/4.
 */
public class AutoSectionView extends FrameLayout {
	private static final String TAG = "AutoSectionView";

	public AutoSectionView(@NonNull Context context) {
		super(context);
		initView();
	}

	public AutoSectionView(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public AutoSectionView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		LayoutInflater.from(this.getContext()).inflate(R.layout.auto_section_layout_one, this, true);
	}

	@Override
	public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
		HiLogger.d(TAG, "requestFocus %s", direction);
		return super.requestFocus(direction, previouslyFocusedRect);
	}
}

package com.runningsnail.demos;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

/**
 * @author yongjie created on 2019/3/18.
 */
public class ShadowView extends View {

	public ShadowView(Context context) {
		this(context,null);
	}

	public ShadowView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs,0);
	}

	public ShadowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
//		ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//		marginLayoutParams.setMargins(0,0, 0,
//				-17);
//		this.setLayoutParams(marginLayoutParams);
	}

	public void handleShadow(boolean hasFocus){
		if(hasFocus){
			this.setBackgroundResource(R.drawable.shadow_test_three);
		}else{
			this.setBackground(null);
		}
	}
}

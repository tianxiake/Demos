package com.runningsnail.demos.activity.tv;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.runningsnail.demos.ShadowLineHelper;
import com.runningsnail.demos.common.utils.HiLogger;

/**
 * @author yongjie created on 2020-02-17.
 */
public class FlyImageView extends FrameLayout {

	//	private ImageView flyImageView;
	private static final String TAG = "FlyImageView";

	ShadowLineHelper shadowLineHelper = new ShadowLineHelper();

	public FlyImageView(Context context) {
		super(context);
		init();
	}

	public FlyImageView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public FlyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		this.setClipChildren(false);
		this.setClipToPadding(false);
//		flyImageView = new ImageView(this.getContext());
//		flyImageView.setImageResource(R.drawable.icon_xie_three);
	}

	@Override
	protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
		HiLogger.d(TAG, "onFocusChanged %s", gainFocus);
		shadowLineHelper.handleShadowLine(gainFocus, this);
//		AnimUtil.scaleView(this,gainFocus);
//		if (gainFocus) {
//			flyImageView.setLayoutParams(new ViewGroup.LayoutParams(400, 400));
//			this.addView(flyImageView);
//			doAnim();
//		} else {
//			this.removeView(flyImageView);
//		}
	}

	private void doAnim() {
//		PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0, this.getWidth() / 2, 0);
//		PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0, this.getHeight() / 2, 0);
//		PropertyValuesHolder translationX = PropertyValuesHolder.ofFloat("TranslationX", -400, 400);
//		PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("TranslationY", -400, 400);
//		PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.1f, 0.3f, 0.5f, 0.7f, 1, 0.7f, 0.5f, 0.3f, 0.1f);
//		ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(flyImageView, alpha, translationX, translationY);
//		objectAnimator.setInterpolator(new AccelerateInterpolator());
//		objectAnimator.setDuration(800);
//		objectAnimator.start();
	}
}

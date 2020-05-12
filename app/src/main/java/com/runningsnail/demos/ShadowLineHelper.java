package com.runningsnail.demos;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.runningsnail.demos.common.utils.HiLogger;

/**
 * 光影效果工具类
 *
 * @author yongjie created on 2020-02-21.
 */
public class ShadowLineHelper {
	private static final String TAG = "ShadowLineHelper";
	private ImageView imageView;
	private FrameLayout frameLayout;
	private int animDuration = 10000;
	private final int IMAGE_WIDTH = 50;
	private boolean shadowLineEnable = true;

	public ShadowLineHelper() {
	}

	public void setAnimDuration(int animDuration) {
		this.animDuration = animDuration;
	}

	public void setShadowLineEnable(boolean shadowLineEnable) {
		this.shadowLineEnable = shadowLineEnable;
	}

	public void handleShadowLine(boolean hasFocus, ViewGroup containerView) {
		if (!shadowLineEnable) {
			return;
		}
		if (hasFocus) {
			int measuredWidth = containerView.getMeasuredWidth();
			int measuredHeight = containerView.getMeasuredHeight();
//			int lineHeight = (int) (Math.sqrt(2) * measuredHeight * 1.1f);
			int lineHeight = (int) (Math.sqrt(measuredHeight * measuredHeight + measuredWidth * measuredWidth) * 1.5f);
			HiLogger.d(TAG, "measuredWidth: %s measuredHeight：%s lineSize:%s", measuredWidth, measuredHeight, lineHeight);
			frameLayout = (FrameLayout) LayoutInflater.from(containerView.getContext()).inflate(R.layout.shadow_line_layout, null);
			imageView = frameLayout.findViewById(R.id.iv_image);
			imageView.setImageResource(R.drawable.icon_shadow_line);
			frameLayout.setLayoutParams(new ViewGroup.LayoutParams(IMAGE_WIDTH, lineHeight));
			containerView.addView(frameLayout);
			doAnim(frameLayout, measuredWidth, measuredHeight, lineHeight);
		} else {
			imageView.setImageDrawable(null);
			containerView.removeView(frameLayout);
			frameLayout = null;
		}
	}

//	private void doAnim(View view, int measuredWidth, int measuredHeight, int lineHeight) {
//		int firstTranslationY = lineHeight / 2;
//		view.setTranslationY(-firstTranslationY);
//		float degrees = (float) Math.toDegrees(Math.atan(measuredHeight / measuredWidth));
//		view.setRotationX(0);
//		view.setRotationY(lineHeight / 2);
//		view.setRotation(degrees);
////		float startX = (float) (Math.sqrt(2) / 4 * lineHeight);
//		float startX = (float) (Math.cos(degrees) * lineHeight / 2);
////		float startY = (float) (Math.sqrt(2) / 4 * lineHeight);
//		float startY = (float) (lineHeight / 2 * Math.sin(degrees));
//////
//		float endY = (float) (lineHeight / 2 * Math.sin(degrees) * Math.tan(degrees));
//		float endX = (float) (Math.tan(degrees) * endY) + measuredWidth;
//
//		HiLogger.d(TAG, "startX:%s startY:%s endX:%s  endY:%s", startX, startY, endX, endY);
////		view.setPivotX(IMAGE_WIDTH);
////		view.setPivotY(0);
////		view.setRotation(45);
////		view.setTranslationX(-IMAGE_WIDTH);
////		float startX = view.getTranslationX();
////		float endX = (float) (startX + Math.sqrt(2) * IMAGE_WIDTH + Math.sqrt(2) * measuredHeight + measuredWidth);
////		HiLogger.d(TAG, "translationStart: %s  translationEnd: %s", view.getTranslationX(), measuredWidth);
//		PropertyValuesHolder translationX = PropertyValuesHolder.ofFloat("TranslationX", -startX, endX);
//		PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("TranslationY", -startY, -endY);
//		PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0f, 0.1f, 0.3f, 0.5f, 0.7f, 1, 0.7f, 0.5f, 0.3f, 0.1f, 0f);
//		ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, alpha, translationY, translationX);
//		objectAnimator.setInterpolator(new AccelerateInterpolator());
//		objectAnimator.setDuration(animDuration);
//		objectAnimator.start();
//	}

	private void doAnim(View view, int measuredWidth, int measuredHeight, int lineHeight) {
		view.setPivotX(IMAGE_WIDTH);
		view.setPivotY(0);
		view.setRotation(45);
		view.setTranslationX(-IMAGE_WIDTH);
		float startX = view.getTranslationX();
		float endX = (float) (startX + Math.sqrt(2) * IMAGE_WIDTH + Math.sqrt(2) * measuredHeight + measuredWidth);
		HiLogger.d(TAG, "translationStart: %s  translationEnd: %s", view.getTranslationX(), measuredWidth);
		PropertyValuesHolder translationX = PropertyValuesHolder.ofFloat("TranslationX", startX, endX);
		PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0f, 0.1f, 0.3f, 0.5f, 0.7f, 1, 0.7f, 0.5f, 0.3f, 0.1f, 0f);
		ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, alpha, translationX);
		objectAnimator.setInterpolator(new AccelerateInterpolator());
		objectAnimator.setDuration(animDuration);
		objectAnimator.start();
	}

}

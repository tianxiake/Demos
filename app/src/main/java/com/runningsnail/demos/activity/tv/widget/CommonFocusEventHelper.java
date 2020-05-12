package com.runningsnail.demos.activity.tv.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;


public class CommonFocusEventHelper {

	public static final String TAG = "CommonFocusEventHelper";

	public static String focusCornerIconUrl = "";

	public static final int FOCUS_BORDER_SIZE = 64;
	public static final int CORNER_ICON_SIZE = 44;

	protected float scaleSize = 1.2f;
	protected int focusBorderDrawable = R.drawable.focus_new;
	protected int borderSize = FOCUS_BORDER_SIZE;
	private AnimatorSet animatorSet;

	protected ImageView focusBorder;
	protected ImageView focusCornerIcon;

	public ImageView getFocusBorder() {
		return focusBorder;
	}

	protected boolean needFocusBorder = true;
	private boolean needAnimation = true;

	public void setNeedAnimation(boolean needAnimation) {
		this.needAnimation = needAnimation;
	}

	public void setScaleSize(float scaleSize) {
		this.scaleSize = scaleSize;
	}

	public void setFocusBorder(int focusBorderDrawable, int borderSize) {
		this.focusBorderDrawable = focusBorderDrawable;
		this.borderSize = borderSize;
	}

	public void setNeedFocusBorder(boolean needFocusBorder) {
		this.needFocusBorder = needFocusBorder;
	}

	public void handleFocus(Context context, boolean hasFocus, ViewGroup container, View focusView) {
		initFocusBorder(context, container, focusView.getWidth(), focusView.getHeight(), hasFocus);
		if (!needAnimation) {
			return;
		}
		if (animatorSet != null) {
			animatorSet.end();
		} else {
			animatorSet = new AnimatorSet();
		}

		if (scaleSize == 1.0f) {
			//	如果scaleSize = 1则认为不放大
			return;
		}
		View animView = container;
		if (container == null) {
			animView = focusView;
		}

		ObjectAnimator scaleXAnimator = hasFocus ?
				ObjectAnimator.ofFloat(animView, "scaleX", animView.getScaleX(), scaleSize + 0.05f, scaleSize) :
				ObjectAnimator.ofFloat(animView, "scaleX", animView.getScaleX(), 1.0f);
		ObjectAnimator scaleYAnimator = hasFocus ?
				ObjectAnimator.ofFloat(animView, "scaleY", animView.getScaleY(), scaleSize + 0.05f, scaleSize) :
				ObjectAnimator.ofFloat(animView, "scaleY", animView.getScaleY(), 1.0f);

		animatorSet.setDuration(hasFocus ? 300 : 100);
		animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
		animatorSet.start();
	}

	private void initFocusBorder(Context context, ViewGroup viewGroup, int width, int height, boolean hasFocus) {
		if (viewGroup == null) {
			return;
		}

		initFocusBorderView(context);

		if (viewGroup instanceof FrameLayout) {
			HiLogger.d(TAG, "viewGroup: height:%s width:%s", viewGroup.getMeasuredHeight(), viewGroup.getMeasuredWidth());
			FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
			layoutParams.setMargins(-borderSize, -borderSize, -borderSize, -borderSize);
			layoutParams.gravity = Gravity.CENTER;
			focusBorder.setLayoutParams(layoutParams);
		} else if (viewGroup instanceof RelativeLayout) {
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
			layoutParams.setMargins(-borderSize, -borderSize, -borderSize, -borderSize);
			layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
			focusBorder.setLayoutParams(layoutParams);
		} else {
			focusBorder.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		}

		if (hasFocus) {
			viewGroup.addView(focusBorder);
		} else {
			viewGroup.removeView(focusBorder);
		}

		initCornerIcon(context, viewGroup, hasFocus);
	}

	/**
	 * 初始化焦点框控件
	 *
	 * @param context
	 */
	private void initFocusBorderView(Context context) {
		if (focusBorder == null) {
			focusBorder = new ImageView(context);
			focusBorder.setImageResource(focusBorderDrawable);
			focusBorder.setScaleType(ImageView.ScaleType.FIT_XY);
			if (!needFocusBorder) {
				focusBorder.setVisibility(View.GONE);
			}
		}
	}

	/**
	 * 初始化焦点框左上角的图片
	 */
	private void initCornerIcon(Context context, ViewGroup viewGroup, boolean hasFocus) {
//		if (!StringUtils.isBlank(focusCornerIconUrl)) {
//			if (focusCornerIcon == null) {
//				focusCornerIcon = new ImageView(context);
//				ImageManager.getInstance().loadImage(focusCornerIcon, focusCornerIconUrl);
//				if (!needFocusBorder) {
//					focusCornerIcon.setVisibility(View.GONE);
//				}
//			}
//
//			if (viewGroup instanceof FrameLayout) {
//				FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(CORNER_ICON_SIZE, CORNER_ICON_SIZE);
//				layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
//				layoutParams.setMargins(-CORNER_ICON_SIZE / 2, -CORNER_ICON_SIZE / 2, 0, 0);
//				focusCornerIcon.setLayoutParams(layoutParams);
//			} else if (viewGroup instanceof RelativeLayout) {
//				RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(CORNER_ICON_SIZE, CORNER_ICON_SIZE);
//				layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//				layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//				layoutParams.setMargins(-CORNER_ICON_SIZE / 2, -CORNER_ICON_SIZE / 2, 0, 0);
//				focusCornerIcon.setLayoutParams(layoutParams);
//			} else {
//				focusCornerIcon.setLayoutParams(new ViewGroup.LayoutParams(CORNER_ICON_SIZE, CORNER_ICON_SIZE));
//			}
//
//			if (hasFocus) {
//				viewGroup.addView(focusCornerIcon);
//			} else {
//				viewGroup.removeView(focusCornerIcon);
//			}
//		}
	}
}

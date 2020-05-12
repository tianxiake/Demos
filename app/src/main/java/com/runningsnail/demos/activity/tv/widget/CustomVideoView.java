package com.runningsnail.demos.activity.tv.widget;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.VideoView;

import com.runningsnail.demos.activity.tv.PageSelectMessage;
import com.runningsnail.demos.common.utils.HiLogger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;

/**
 * @author yongjie created on 2020/4/13.
 */
public class CustomVideoView extends FrameLayout {
	private static final String TAG = "CustomVideoView";
	private String url = "http://vfx.mtime.cn/Video/2019/06/26/mp4/190626111517361726.mp4";

	private boolean isTaskStart = false;
	private VideoView videoView;

	public CustomVideoView(Context context) {
		super(context);
		initView();
	}

	public CustomVideoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		videoView = new VideoView(this.getContext());
		videoView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		this.addView(videoView);
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void handleVisible(PageSelectMessage pageSelectMessage) {
		boolean cover = isViewCovered(this);
		HiLogger.d(TAG, "handleVisible: %s", cover);
		if (cover) {
			stopTask();
		} else {
			startTask();
		}
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		EventBus.getDefault().register(this);
	}


	private void startTask() {
		if (isTaskStart) {
			return;
		}
		boolean cover = isViewCovered(this);
		if (cover) {
			return;
		}
		videoView.stopPlayback();
		videoView.setVideoURI(Uri.parse(url));
		videoView.start();
		isTaskStart = true;
		HiLogger.d(TAG, "startTask");
	}

	private void stopTask() {
		if (!isTaskStart) {
			return;
		}
		isTaskStart = false;
		videoView.stopPlayback();
		HiLogger.d(TAG, "stopTask");
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		EventBus.getDefault().unregister(this);
	}


	protected boolean isInScreen(View view) {
		Rect globalRect = new Rect();
		boolean global = view.getGlobalVisibleRect(globalRect);
		Rect localRect = new Rect();
		boolean local = view.getLocalVisibleRect(localRect);
		HiLogger.d(TAG, "globalRect:%s global:%s localRect:%s local:%s", globalRect, global, localRect, local);
		int[] locationOnWindow = new int[2];
		this.getLocationInWindow(locationOnWindow);
		HiLogger.d(TAG, "locationOnWindow: %s", Arrays.toString(locationOnWindow));
		if (globalRect.width() != 0 && globalRect.height() != 0) {
			boolean equals = globalRect.equals(localRect);
			if (equals) {
				return true;
			} else {
				if (locationOnWindow[0] == globalRect.left
						&& locationOnWindow[1] == globalRect.top
						&& locationOnWindow[0] == localRect.left
						&& locationOnWindow[1] == localRect.top) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isViewCovered(final View view) {
		Rect rect = new Rect();
		boolean global = view.getGlobalVisibleRect(rect);
		Rect localRect = new Rect();
		boolean local = view.getLocalVisibleRect(localRect);
		HiLogger.d(TAG, "globalCover:%s global:%s  localCover:%s local:%s", global, rect, local, localRect);
		int[] locationOnWindow = new int[2];
		this.getLocationInWindow(locationOnWindow);
		HiLogger.d(TAG, "locationOnWindow: %s", Arrays.toString(locationOnWindow));
		if (global) {
			int measuredWidth = view.getMeasuredWidth();
			int measuredHeight = view.getMeasuredHeight();
			HiLogger.d(TAG, "measuredWidth:%s measuredHeight:%s rectWidth:%s rectHeight:%s", measuredWidth, measuredHeight, rect.width(), rect.height());
			if (rect.width() >= measuredWidth && rect.height() >= measuredHeight) {
				return !global;
			}
		}
//		View currentView = view;
//
//		Rect currentViewRect = new Rect();
//		boolean partVisible = currentView.getGlobalVisibleRect(currentViewRect);
//		boolean totalHeightVisible = (currentViewRect.bottom - currentViewRect.top) >= view.getMeasuredHeight();
//		boolean totalWidthVisible = (currentViewRect.right - currentViewRect.left) >= view.getMeasuredWidth();
//		boolean totalViewVisible = partVisible && totalHeightVisible && totalWidthVisible;
//		if (!totalViewVisible)
//			return true;
//
//		while (currentView.getParent() instanceof ViewGroup) {
//			ViewGroup currentParent = (ViewGroup) currentView.getParent();
//			// if the parent of view is not visible,return true
//			if (currentParent.getVisibility() != View.VISIBLE)
//				return true;
//
//			int start = indexOfViewInParent(currentView, currentParent);
//			for (int i = start + 1; i < currentParent.getChildCount(); i++) {
//				Rect viewRect = new Rect();
//				view.getGlobalVisibleRect(viewRect);
//				View otherView = currentParent.getChildAt(i);
//				Rect otherViewRect = new Rect();
//				otherView.getGlobalVisibleRect(otherViewRect);
//				// if view intersects its older brother(covered),return true
//				if (Rect.intersects(viewRect, otherViewRect))
//					return true;
//			}
//			currentView = currentParent;
//		}
		return false;
	}

	private int indexOfViewInParent(View view, ViewGroup parent) {
		int index;
		for (index = 0; index < parent.getChildCount(); index++) {
			if (parent.getChildAt(index) == view)
				break;
		}
		return index;
	}


	/**
	 * 检测制定View是否被遮住显示不全
	 *
	 * @return
	 */
	protected boolean isCover(View view) {
		Rect rect = new Rect();
		boolean global = view.getGlobalVisibleRect(rect);
		//TESTCODE: START liuyongjie, 2020/4/13, .
		Rect localRect = new Rect();
		boolean local = view.getLocalVisibleRect(localRect);
		HiLogger.d(TAG, "globalCover:%s global:%s  localCover:%s local:%s", global, rect, local, localRect);
		//TESTCODE: END liuyongjie, 2020/4/13, .
		if (global) {
			int measuredWidth = view.getMeasuredWidth();
			int measuredHeight = view.getMeasuredHeight();
			HiLogger.d(TAG, "measuredWidth:%s measuredHeight:%s rectWidth:%s rectHeight:%s", measuredWidth, measuredHeight, rect.width(), rect.height());
			if (rect.width() >= measuredWidth && rect.height() >= measuredHeight) {
				return !global;
			}
		}
		return true;
	}

	@Override
	protected void onWindowVisibilityChanged(int visibility) {
		super.onWindowVisibilityChanged(visibility);
		HiLogger.d(TAG, "onWindowVisibilityChanged %s", visibility);
		if (visibility == View.VISIBLE) {
			startTask();
		} else {
			stopTask();
		}
	}

//
//	@Override
//	public void onWindowFocusChanged(boolean hasWindowFocus) {
//		super.onWindowFocusChanged(hasWindowFocus);
//		HiLogger.d(TAG, "onWindowFocusChanged %s", hasWindowFocus);
//		if (hasWindowFocus) {
//			boolean cover = isInScreen(this);
//			if (cover) {
//				startTask();
//			} else {
//				stopTask();
//			}
//		} else {
//			stopTask();
//		}
//	}
}

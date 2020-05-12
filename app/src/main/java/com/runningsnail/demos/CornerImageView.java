package com.runningsnail.demos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.runningsnail.demos.common.utils.DensityUtils;
import com.runningsnail.demos.common.utils.HiLogger;


/**
 * 可以绘制一个角标的ImageView
 *
 * @author yongjie created on 2019-11-14.
 */
public class CornerImageView extends AppCompatImageView {
	private static final String TAG = "CornerImageView";

	/**
	 * 角标位置
	 */
	public enum CornerLocation {
		/**
		 * 左上角
		 */
		TOP_LEFT,
		/**
		 * 右上角
		 */
		TOP_RIGHT,
		/**
		 * 左下角
		 */
		BOTTOM_LEFT,
		/**
		 * 右下角
		 */
		BOTTOM_RIGHT;
	}

	private Paint paint = new Paint();

	private CornerLocation cornerLocation = CornerLocation.TOP_LEFT;

	public CornerImageView(Context context) {
		super(context);
		init();
	}


	public CornerImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CornerImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		paint.setAntiAlias(true);
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		HiLogger.i(TAG, "onMeasure");
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		HiLogger.i(TAG, "onSizeChanged w:%s,h:%s,oldw:%s,oldh:%s", w, h, oldw, oldh);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		HiLogger.i(TAG, "onLayout");
	}


	@Override
	protected void onDraw(Canvas canvas) {
		HiLogger.i(TAG, "onDraw start");
		int layerCount = canvas.saveLayer(0, 0, this.getWidth(), this.getHeight(), null, Canvas.ALL_SAVE_FLAG);
		super.onDraw(canvas);
		//draw角标
		//draw圆角
		Path path = new Path();
		path.addRoundRect(new RectF(0, 0, this.getWidth(), this.getHeight()), DensityUtils.dp2px(this.getContext(), 20),
				DensityUtils.dp2px(this.getContext(), 20), Path.Direction.CW);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		canvas.drawPath(path, paint);
		paint.setXfermode(null);
		canvas.restoreToCount(layerCount);
		HiLogger.i(TAG, "onDraw end");
	}


	private float[] getCornerDrawLocation(Drawable drawable, float cornerMargin) {
		float[] location = new float[2];
		int measuredWidth = getMeasuredWidth();
		int measuredHeight = getMeasuredHeight();
		int bitmapWidth = drawable.getIntrinsicWidth();
		int bitmapHeight = drawable.getIntrinsicHeight();
		switch (cornerLocation) {
			case TOP_LEFT:
				location[0] = cornerMargin;
				location[1] = cornerMargin;
				break;
			case TOP_RIGHT:
				location[0] = measuredWidth - bitmapWidth - cornerMargin;
				location[1] = cornerMargin;
				break;
			case BOTTOM_LEFT:
				location[0] = cornerMargin;
				location[1] = measuredHeight - bitmapHeight - cornerMargin;
				break;
			case BOTTOM_RIGHT:
				location[0] = measuredWidth - bitmapWidth - cornerMargin;
				location[1] = measuredHeight - bitmapHeight - cornerMargin;
				break;
			default:
				break;
		}
		return location;
	}

	private void drawDrawable(Canvas canvas, Drawable cornerDrawable, float cornerMargin) {
		canvas.save();
		float[] cornerDrawLocation = getCornerDrawLocation(cornerDrawable, cornerMargin);
		int left = (int) cornerDrawLocation[0];
		int top = (int) cornerDrawLocation[1];
		cornerDrawable.setBounds(left, top, cornerDrawable.getIntrinsicWidth() + left,
				cornerDrawable.getIntrinsicHeight() + top);
		cornerDrawable.draw(canvas);
		canvas.restore();
	}

	public void addCornerUrl(String url, CornerLocation cornerLocation, float cornerMargin) {
//		Glide.with(this.getContext()).asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
//			@Override
//			public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//			}
//		};
	}

//	public void addCornerDrawable(Drawable drawable, CornerLocation cornerLocation, float cornerMargin) {
//
//	}

//	public void setCornerImg(int resId) {
//		cornerDrawable = this.getResources().getDrawable(resId);
//		invalidate();
//	}

//	public void setCornerImg(String url) {
//		Logger.d(TAG, url);
//		this.cornerDrawable = null;
//		Glide.with(this).asDrawable().load(url).into(new SimpleTarget<Drawable>() {
//			@Override
//			public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//				Logger.i(TAG, "onResourceReady");
//				cornerDrawable = resource;
//				invalidate();
//			}
//		});
//	}

//	public void setCornerImg(Drawable drawable) {
//		this.cornerDrawable = drawable;
//		invalidate();
//	}
//
//	public void setCornerMargin(float cornerMargin) {
//		this.cornerMargin = cornerMargin;
//		invalidate();
//	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		HiLogger.i(TAG, "onDetachedFromWindow");
	}

	public void reset() {
//		this.cornerMargin = 0;
		this.cornerLocation = CornerLocation.TOP_LEFT;
//		this.cornerDrawable = null;
	}

	private static class CornerEntity {
		public String url;

	}


}

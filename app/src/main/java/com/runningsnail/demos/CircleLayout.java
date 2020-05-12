package com.runningsnail.demos;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CircleLayout extends FrameLayout {
	public static final String TAG = "CircleCornerLayout";
	private float radius;
	private boolean isCircleCorner;
	private Paint paint;

	public CircleLayout(@NonNull Context context) {
		this(context, (AttributeSet) null);
	}

	public CircleLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CircleLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.radius = 0.0F;
		this.isCircleCorner = false;
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleCornerLayout);
		this.isCircleCorner = typedArray.getBoolean(R.styleable.CircleCornerLayout_isCircle, false);
		this.radius = typedArray.getDimension(R.styleable.CircleCornerLayout_circleRadius, 0.0F);
		typedArray.recycle();
		this.setIsCircleCorner(this.isCircleCorner, this.radius);
	}

	public void setIsCircleCorner(boolean isCircleCorner, float radius) {
		this.isCircleCorner = isCircleCorner;
		this.radius = radius;
		if (isCircleCorner) {
			this.setWillNotDraw(false);
			this.setClipChildren(false);
			this.setClipToPadding(false);
			this.paint = new Paint();
			this.paint.setAntiAlias(true);
			this.paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		}

	}

	@Override
	public void draw(Canvas canvas) {
		Log.i("CircleCornerLayout", "draw");

		try {
			if (canvas != null) {
				if (this.isCircleCorner) {
					this.drawShapePathCanvas(canvas);
				} else {
					super.draw(canvas);
				}
			}
		} catch (Exception var3) {
			var3.printStackTrace();
		}

	}

	private void drawShapePathCanvas(Canvas shapeCanvas) {
		if (shapeCanvas != null) {
			int width = this.getWidth();
			int height = this.getHeight();
			if (width == 0 || height == 0) {
				return;
			}

			int count = shapeCanvas.save();
			int count2 = shapeCanvas.saveLayer(0.0F, 0.0F, (float) width, (float) height, (Paint) null, 31);
			Path path = DrawUtils.addRoundPath3(width, height, this.radius);
			super.draw(shapeCanvas);
			shapeCanvas.drawPath(path, this.paint);
			if (count2 > 0) {
				shapeCanvas.restoreToCount(count2);
			}

			shapeCanvas.restoreToCount(count);
		}

	}
}

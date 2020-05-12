package com.runningsnail.demos.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.runningsnail.demos.R;
import com.runningsnail.demos.activity.DisplayUtil;
import com.runningsnail.demos.common.utils.HiLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yongjie created on 2020/4/13.
 */
public class LineView extends LinearLayout {
	private static final String TAG = "LineView";
	private final int lineHeight;
	private float especialTextSize;
	private int maxShowLine;
	private float textSize;
	private int especialTextColor;
	private String especialText;
	private int lineTextColor;
	private boolean sizeChange = true;
	private String content = "";
	private int measureWidth;
	private int lineInterval;
	private OnLineViewListener onLineViewListener;

	public OnLineViewListener getOnLineViewListener() {
		return onLineViewListener;
	}

	public void setOnLineViewListener(OnLineViewListener onLineViewListener) {
		this.onLineViewListener = onLineViewListener;
	}

	public LineView(Context context) {
		this(context, null);
	}

	public LineView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.setOrientation(LinearLayout.VERTICAL);
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.lineView);
		textSize = typedArray.getDimension(R.styleable.lineView_lineTextSize, 24);
		especialTextSize = (int) typedArray.getDimension(R.styleable.lineView_especialTextSize, 16f);
		lineHeight = (int) typedArray.getDimension(R.styleable.lineView_lineHeight, 24f);
		maxShowLine = typedArray.getInteger(R.styleable.lineView_maxShowLine, 2);
		lineTextColor = typedArray.getColor(R.styleable.lineView_lineTextColor, Color.parseColor("#FFFFFF"));
		especialTextColor = typedArray.getColor(R.styleable.lineView_especialTextColor, Color.parseColor("#00CFF3"));
		lineInterval = typedArray.getDimensionPixelSize(R.styleable.lineView_lineInterval, 6);
		typedArray.recycle();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		Log.i(TAG, "onMeasure start");
		handleText();
		Log.i(TAG, "onMeasure end");
	}

	private void handleText() {
		if (this.getMeasuredWidth() != measureWidth) {
			measureWidth = this.getMeasuredWidth();
			if (!TextUtils.isEmpty(content)) {
				//处理文字切割和文本展示
				List<String> strings = spiltContentByWidth(content, measureWidth);
				if (strings.isEmpty()) {
					if (onLineViewListener != null) {
						onLineViewListener.onNoData();
					}
				} else {
					List<String> showList;
					if (strings.size() > maxShowLine) {
						showList = strings.subList(0, maxShowLine);
					} else {
						showList = strings;
					}
					if (!TextUtils.isEmpty(especialText)) {
						String endLineText = showList.get(showList.size() - 1);
						Paint paint = new Paint();
						paint.setTextSize(DisplayUtil.sp2px(this.getContext(), getTextSize()));
						int especialTextWidth = 0;
						if (showList.size() >= maxShowLine) {
							especialTextWidth = (int) Math.ceil(paint.measureText("..." + especialText));
						} else {
							especialTextWidth = (int) Math.ceil(paint.measureText(especialText));
						}
						int showDiffWidth = measureWidth - especialTextWidth;
						int indexStr = paint.breakText(endLineText, true, showDiffWidth, null);
						if (showList.size() >= maxShowLine) {
							endLineText = endLineText.substring(0, indexStr + 1) + "..." + especialText;
						} else {
							endLineText = endLineText.substring(0, indexStr + 1) + especialText;
						}
						showList.set(showList.size() - 1, endLineText);
					}
					addTextView(showList);
					if (onLineViewListener != null) {
						onLineViewListener.onShowData();
					}
				}
			} else {
				if (onLineViewListener != null) {
					onLineViewListener.onNoData();
				}
			}
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		sizeChange = true;
	}

	public int getMaxShowLine() {
		return maxShowLine;
	}

	public void setMaxShowLine(int maxShowLine) {
		this.maxShowLine = maxShowLine;
	}

	public float getTextSize() {
		return textSize;
	}

	/**
	 * 单位是sp
	 */
	public void setTextSize(float textSize) {
		this.textSize = textSize;
	}

	public int getEspecialTextColor() {
		return especialTextColor;
	}

	public void setEspecialTextColor(int especialTextColor) {
		this.especialTextColor = especialTextColor;
	}

	public int getLineTextColor() {
		return lineTextColor;
	}

	public void setLineTextColor(int lineTextColor) {
		this.lineTextColor = lineTextColor;
	}

	public void bindData(String content, String especialText) {
		this.content = content;
		this.especialText = especialText;
	}

	private void addTextView(List<String> content) {
		for (int i = 0; i < content.size(); i++) {
			String showStr = content.get(i);
			if (i == content.size() - 1) {
				TextView textView;
				if (i == 0) {
					textView = createTextView(0, getTextSize());
				} else {
					textView = createTextView(lineInterval, getTextSize());
				}
				int startIndex = showStr.lastIndexOf(especialText);
				SpannableString spannableString = new SpannableString(showStr);
				spannableString.setSpan(new VerticalCenterSpan(especialTextSize, getEspecialTextColor()), startIndex, showStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
				textView.setText(spannableString);
				this.addView(textView);
			} else if (i == 0) {
				TextView textView = createTextView(0, getTextSize());
				textView.setText(showStr);
				textView.setTextColor(getLineTextColor());
				this.addView(textView);
			} else {
				TextView textView = createTextView(lineInterval, getTextSize());
				textView.setTextColor(getLineTextColor());
				textView.setText(showStr);
				this.addView(textView);
			}
		}
	}

	private TextView createTextView(int topMargin, float textSize) {
		TextView textView = new TextView(this.getContext());
		LayoutParams layoutParams;
		if (lineHeight != 0) {
			layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, lineHeight);
		} else {
			layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		}
		textView.setGravity(Gravity.CENTER | Gravity.LEFT);
		layoutParams.topMargin = topMargin;
		textView.setTextSize(textSize);
		textView.setBackgroundColor(Color.BLUE);
		textView.setLayoutParams(layoutParams);
		return textView;
	}

	private List<String> spiltContentByWidth(String content, int width) {
		Paint paint = new Paint();
		List<String> lists = new ArrayList<>();
		int tempStart = 0;
		while (tempStart < content.length() - 1) {
			HiLogger.d(TAG, "textSize sp: %s", textSize);
			paint.setTextSize(DisplayUtil.sp2px(this.getContext(), getTextSize()));
			int charNumber = paint.breakText(content, tempStart, content.length(), true, width, null);
			String substring = content.substring(tempStart, tempStart + charNumber);
			HiLogger.d(TAG, "substring: %s", substring);
			lists.add(substring);
			tempStart += charNumber;
		}
		return lists;
	}

	public interface OnLineViewListener {
		void onNoData();

		void onShowData();
	}

}

package com.runningsnail.demos.activity.tv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.AnimUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShadowEffectActivity extends AppCompatActivity {

	public static final String TAG = "ShadowEffectActivity";
	@BindView(R.id.btn_one)
	Button btnOne;
	@BindView(R.id.fl_container)
	FrameLayout flContainer;
//	@BindView(R.id.fl_focus)
//	RelativeLayout flFocus;
//	@BindView(R.id.iv_img)
//	ImageView ivImg;
//	@BindView(R.id.shadow_view)
//	ShadowView shadowView;
//	@BindView(R.id.fl_inner)
//	FrameLayout flInner;

	private boolean click = false;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shadow_effect);
		ButterKnife.bind(this);
		flContainer.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				AnimUtil.scaleView(v, hasFocus);
			}
		});


//		flFocus.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//			@Override
//			public void onFocusChange(View v, boolean hasFocus) {
//				if (hasFocus) {
////					flInner.setBackgroundResource(R.drawable.shadow_drawable_two);
////					ShadowDrawable.setShadowDrawable(flInner, 0,
////							Color.parseColor("#EE757575"), 12, 0, 12);
//////					setShadowDrawable(flFocus,0,Color.GRAY,30,20,20);
//					HiLogger.d(TAG, "Image focus size: height:" + ivImg.getHeight() + ",width:" + ivImg.getWidth());
//				} else {
//					HiLogger.d(TAG, "Image Lost focus size: height:" + ivImg.getHeight() + ",width:" + ivImg.getWidth());
////					flFocus.setBackgroundColor(Color.RED);
////					flInner.setBackgroundColor(Color.BLUE);
//				}
//				shadowView.handleShadow(hasFocus);
//				AnimUtil.scaleView(ivImg, hasFocus);
//			}
//		});
	}

//	public static void setShadowDrawable(View view, int shapeRadius, int shadowColor, int shadowRadius, int offsetX, int offsetY) {
//		ShadowDrawable drawable = new ShadowDrawable.Builder()
//				.setShapeRadius(shapeRadius)
//				.setShadowColor(shadowColor)
//				.setShadowRadius(shadowRadius)
//				.setOffsetX(offsetX)
//				.setOffsetY(offsetY)
//				.builder();
//		view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//		ViewCompat.setBackground(view, drawable);
//	}


}

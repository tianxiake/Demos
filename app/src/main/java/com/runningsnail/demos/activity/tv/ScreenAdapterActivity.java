package com.runningsnail.demos.activity.tv;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.runningsnail.demos.CornerImageView;
import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.AnimUtil;
import com.runningsnail.demos.common.utils.HiLogger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ScreenAdapterActivity extends AppCompatActivity {
	private static final String TAG = "ScreenAdapterActivity";
	@BindView(R.id.container)
	ViewGroup container;
	@BindView(R.id.iv_image)
	CornerImageView ivImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_adapter);
		ButterKnife.bind(this);

		ivImage.setImageResource(R.drawable.icon_waixingren);

		ivImage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				HiLogger.d(TAG, "hasFocus:%s", hasFocus);
				AnimUtil.scaleView(v, hasFocus);
			}
		});
	}

	private void loadCornerBackground(View view, int resId, String cornerLocation) {
		RoundedCornersTransformation roundedCornersTransformation = null;

		switch (cornerLocation) {
			case "1":
				roundedCornersTransformation = new RoundedCornersTransformation(20, 0, RoundedCornersTransformation.CornerType.TOP_LEFT);
				break;
			case "2":
				roundedCornersTransformation = new RoundedCornersTransformation(20, 0, RoundedCornersTransformation.CornerType.BOTTOM_LEFT);
				break;
			case "3":
				roundedCornersTransformation = new RoundedCornersTransformation(20, 0, RoundedCornersTransformation.CornerType.BOTTOM_RIGHT);
				break;
			case "4":
				roundedCornersTransformation = new RoundedCornersTransformation(20, 0, RoundedCornersTransformation.CornerType.TOP_RIGHT);
				break;
			default:
				roundedCornersTransformation = new RoundedCornersTransformation(20, 0);
				break;
		}

		RequestOptions requestOptions = new RequestOptions();
		requestOptions.transform(roundedCornersTransformation)
				.diskCacheStrategy(DiskCacheStrategy.ALL);
		Glide.with(view.getContext()).load(view.getResources().getDrawable(resId)).into(new SimpleTarget<Drawable>() {
			@Override
			public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
				view.setBackground(resource);
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	public static void main(String[] args) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date parse = simpleDateFormat.parse("20171217000000");
			SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MMdd");
			String format = simpleDateFormat2.format(parse);
			System.out.println(format);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}

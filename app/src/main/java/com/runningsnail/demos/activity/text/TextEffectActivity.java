package com.runningsnail.demos.activity.text;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.AnimUtil;
import com.runningsnail.demos.widget.LineView;
import com.runningsnail.demos.widget.VerticalCenterSpan;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextEffectActivity extends AppCompatActivity {

	@BindView(R.id.tv_title)
	TextView tvTitle;
	@BindView(R.id.line_view)
	LineView lineView;
	@BindView(R.id.tv_hint_text)
	TextView tvHintText;
	@BindView(R.id.ll_hint_group)
	LinearLayout llHintGroup;
	@BindView(R.id.tv_hint2_text)
	TextView tvHint2Text;
	private String content = "今天天气不错今天天气不错今天天气不错今天天气不错今天天气不错今天天气不错今天天气不错今天天气不错今天天气不错今天天气不错今天天气不错今天天气不错今天天气不错今天天气不错今天天气不错";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text_effect);
		ButterKnife.bind(this);
		SpannableString spannableString = new SpannableString("今天天气不错");
		//修改文字的大小
		spannableString.setSpan(new AbsoluteSizeSpan(60), 2, 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
		spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#ffffff")), 2, 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
		spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#00ff00")), 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
		spannableString.setSpan(new VerticalCenterSpan(10, Color.parseColor("#00ff00")), 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
		spannableString.setSpan(new BackgroundColorSpan(Color.parseColor("#999999")), 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
		tvTitle.setText(spannableString);
		lineView.bindData(content, "[更多]");

//		SpannableString hintString = new SpannableString("今天天气不错今天天气不错今天天气不错");
//		hintString.setSpan(new VerticalCenterSpan(15, Color.parseColor("#00ff00")), 0, hintString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
		tvHintText.setText("今天天气不错今天天气不错今天天气不错");

		tvHint2Text.setText("今天天气不错今天天气不错今天天气不错");
	}
}

package com.runningsnail.demos.activity.tv.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.AnimUtil;

/**
 * @author yongjie created on 2020/10/20.
 */
public class ClipCaseFiveFragment extends Fragment {

	private View view;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = LayoutInflater.from(container.getContext()).inflate(R.layout.clip_case_five_fragment, container, false);
		this.view = view.findViewById(R.id.text_one);
		initListener();
		return view;
	}

	private void initListener() {
		view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				AnimUtil.scaleView(v, hasFocus);
			}
		});
	}
}

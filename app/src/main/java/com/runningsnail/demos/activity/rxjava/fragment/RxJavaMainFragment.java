package com.runningsnail.demos.activity.rxjava.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.runningsnail.demos.R;
import com.runningsnail.demos.base.BaseDemoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author yongjie created on 2021/1/14.
 */
public class RxJavaMainFragment extends BaseDemoFragment {

	@BindView(R.id.btn_test_subscribe_on)
	Button testSubscribeOn;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_main_layout, container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	@OnClick({R.id.btn_test_subscribe_on})
	public void onClick(View view) {
		int id = view.getId();
		switch (id) {
			case R.id.btn_test_subscribe_on:
				getOperationInf().replaceFragment(new SubscribeOnFragment());
				break;
			default:
				break;
		}
	}
}

package com.runningsnail.demos.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author yongjie created on 2021/1/14.
 */
public class BaseDemoFragment extends Fragment {

	private OperationInf operationInf;

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		operationInf = (OperationInf) this.getActivity();
	}

	public OperationInf getOperationInf() {
		return operationInf;
	}
}

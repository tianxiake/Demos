package com.runningsnail.demos.activity.retrofit.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.runningsnail.demos.R;
import com.runningsnail.demos.activity.retrofit.RetrofitService;
import com.runningsnail.demos.activity.retrofit.TestRetrofitFactory;
import com.runningsnail.demos.common.utils.HiLogger;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author yongjie created on 2020/10/23.
 */
public class RetrofitOneFragment extends Fragment {
	private static final String TAG = "RetrofitOneFragment";

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = LayoutInflater.from(container.getContext()).inflate(R.layout.retrofit_one_fragment, container, false);
		initData();
		return view;
	}

	private void initData() {
		RetrofitService testDataService = TestRetrofitFactory.createTestDataOneService();
		Call<ResponseBody> testDataOne = testDataService.getTestDataOne();
		testDataOne.enqueue(new Callback<ResponseBody>() {
			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				try {
					String string = response.body().string();
					HiLogger.d(TAG, "body: %s", string);
				} catch (IOException e) {

				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {

			}
		});
	}
}

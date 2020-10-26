package com.runningsnail.demos.activity.retrofit;

import retrofit2.Retrofit;

/**
 * @author yongjie created on 2020/10/25.
 */
public class TestRetrofitFactory {

	public static RetrofitService createTestDataOneService() {
		Retrofit build = new Retrofit.Builder().
				baseUrl("http://mock-api.com/bKkpAlzB.mock/")
				.build();

		RetrofitService retrofitService = build.create(RetrofitService.class);
		return retrofitService;
	}
}

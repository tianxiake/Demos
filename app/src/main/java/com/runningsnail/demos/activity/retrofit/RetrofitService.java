package com.runningsnail.demos.activity.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * mock base Url
 * http://mock-api.com/bKkpAlzB.mock
 *
 * @author yongjie created on 2020/10/23.
 */
public interface RetrofitService {

//	@GET("test/data")
//	Call<Response<TestData>> getTestData();

	@GET("test/data")
	Call<ResponseBody> getTestDataOne();

	@GET("test/data")
	Call<TestData> getTestDataTwo();
}

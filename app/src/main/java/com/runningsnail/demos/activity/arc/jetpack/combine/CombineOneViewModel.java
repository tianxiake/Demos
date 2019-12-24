package com.runningsnail.demos.activity.arc.jetpack.combine;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.runningsnail.demos.common.utils.HiLogger;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author yongjie created on 2019-11-15.
 */
public class CombineOneViewModel extends AndroidViewModel {
	private static final String TAG = "CombineOneViewModel";
	private MutableLiveData<String> nameLiveData;
	private Disposable nameDisposable;

	public CombineOneViewModel(@NonNull Application application) {
		super(application);
	}

	/**
	 * 定义LiveData的访问方法,与Databinding一起使用的时候
	 * LiveData数据发生改变时此方法就会被调用一次,所以要注意多次调用的问题
	 */
	public LiveData<String> getNameLiveData() {
		HiLogger.e(TAG, "getNameLiveData", new Throwable());
		if (nameLiveData == null) {
			nameLiveData = new MutableLiveData<>();
			loadName();
		}
		return nameLiveData;
	}

	private void loadName() {
		Observable.interval(3, TimeUnit.SECONDS)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Observer<Long>() {
					@Override
					public void onSubscribe(Disposable d) {
						nameDisposable = d;
					}

					@Override
					public void onNext(Long s) {
						HiLogger.i(TAG, "interval: %s", s);
						nameLiveData.setValue(s + "_哈哈");
					}

					@Override
					public void onError(Throwable e) {

					}

					@Override
					public void onComplete() {

					}
				});

	}

	@Override
	protected void onCleared() {
		super.onCleared();
		HiLogger.i(TAG, "onCleared");
		if (nameDisposable != null) {
			boolean disposed = nameDisposable.isDisposed();
			if (!disposed) {
				nameDisposable.dispose();
			}
		}
	}
}

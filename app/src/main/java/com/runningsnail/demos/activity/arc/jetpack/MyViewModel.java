package com.runningsnail.demos.activity.arc.jetpack;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.runningsnail.demos.common.utils.HiLogger;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author yongjie created on 2019-11-13.
 */
public class MyViewModel extends AndroidViewModel {

	private static final String TAG = "MyViewModel";
	/**
	 * LiveData有两个方法 setValue和postValue
	 * setValue方法只能在主线程进行执行否则会抛错误
	 * postValue可以在子线程中调用,内部会把包装好的数据发射到主线程执行
	 */
	private MutableLiveData<String> nameLiveData;
	private Disposable disposable;

	public MyViewModel(@NonNull Application application) {
		super(application);
	}

	public MutableLiveData<String> getNameLiveData() {
		if (nameLiveData == null) {
			nameLiveData = new MutableLiveData<>();
		}
		loadName();
		return nameLiveData;
	}

	private void loadName() {
		//这种写法销毁不进行回收是会产生泄漏的
		Observable.interval(3, TimeUnit.SECONDS)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Observer<Long>() {
					@Override
					public void onSubscribe(Disposable d) {
						disposable = d;
					}

					@Override
					public void onNext(Long aLong) {
						HiLogger.i(TAG, "onNext");
						nameLiveData.setValue(aLong + "_" + aLong + "哈哈");
					}

					@Override
					public void onError(Throwable e) {

					}

					@Override
					public void onComplete() {

					}
				});
	}

	/**
	 * 重写ViewModel的onClear方法来进行资源回收的释放
	 */
	@Override
	protected void onCleared() {
		super.onCleared();
		HiLogger.i(TAG, "onCleared");
		if (disposable != null) {
			disposable.dispose();
		}
	}
}

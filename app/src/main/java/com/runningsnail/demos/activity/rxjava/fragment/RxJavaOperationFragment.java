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
import com.runningsnail.demos.common.utils.HiLogger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaOperationFragment extends BaseDemoFragment {
	private static final String TAG = "RxJavaOperationFragment";
	@BindView(R.id.btn_concat_map_operation)
	Button btnConcatMap;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_operation_layout, container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	@OnClick({R.id.btn_concat_map_operation})
	public void onClick(View view) {
		int id = view.getId();
		switch (id) {
			case R.id.btn_concat_map_operation:
				concatMapOperation();
				break;
			default:
				break;
		}
	}

	/**
	 * 测试: concatMap操作符,有一个observable发送失败了,后续的任务不在执行
	 * 结论: 自定义的Observable要正确的发送onNext、onComplete、onError事件
	 */
	private void concatMapOperation() {
		Observable.create(new ObservableOnSubscribe<String>() {
			@Override
			public void subscribe(@io.reactivex.annotations.NonNull ObservableEmitter<String> emitter) throws Exception {
				HiLogger.i(TAG, "observable1");
//				emitter.onNext("1");
				emitter.onError(null);
			}
		}).concatMap(new Function<String, ObservableSource<String>>() {
			@Override
			public ObservableSource<String> apply(@io.reactivex.annotations.NonNull String s) throws Exception {
				HiLogger.i(TAG, "apply: %s", s);
				return Observable.create(new ObservableOnSubscribe<String>() {
					@Override
					public void subscribe(@io.reactivex.annotations.NonNull ObservableEmitter<String> emitter) throws Exception {
						HiLogger.i(TAG, "observable2");
						emitter.onNext("2");
					}
				});
			}
		}).subscribeOn(Schedulers.io())
				.subscribe(new Observer<String>() {
					@Override
					public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

					}

					@Override
					public void onNext(@io.reactivex.annotations.NonNull String s) {
						HiLogger.i(TAG, "onNext: %s", s);
					}

					@Override
					public void onError(@io.reactivex.annotations.NonNull Throwable e) {
						HiLogger.e(TAG, "onError", e);
					}

					@Override
					public void onComplete() {
						HiLogger.i(TAG, "onComplete");
					}
				});


	}

}

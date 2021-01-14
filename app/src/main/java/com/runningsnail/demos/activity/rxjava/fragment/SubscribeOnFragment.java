package com.runningsnail.demos.activity.rxjava.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author yongjie created on 2021/1/14.
 */
public class SubscribeOnFragment extends Fragment {
	private static final String TAG = "SubscribeOnFragment";

	@BindView(R.id.btn_test_exchange_thread)
	Button testExchangeThread;
	@BindView(R.id.btn_test_invoke_twice)
	Button testInvokeTwice;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_subscribe_on_layout, container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	@OnClick({R.id.btn_test_invoke_twice, R.id.btn_test_exchange_thread})
	public void onClick(View view) {
		int id = view.getId();
		switch (id) {
			case R.id.btn_test_invoke_twice:
				testInvokeTwice();
				break;
			case R.id.btn_test_exchange_thread:
				testExchangeThread();
				break;
			default:
				break;
		}

	}

	private void testInvokeTwice() {
		Observable.just(1, 2, 3)
				.subscribeOn(Schedulers.io())
				.subscribeOn(AndroidSchedulers.mainThread())
				.map(new Function<Integer, Integer>() {
					@Override
					public Integer apply(Integer integer) throws Exception {
						HiLogger.i(TAG, "map==> %s", integer);
						return integer;
					}
				})
				.subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(new Observer<Integer>() {
					@Override
					public void onSubscribe(Disposable d) {

					}

					@Override
					public void onNext(Integer integer) {
						HiLogger.i(TAG, "onNext==> %s", integer);
					}

					@Override
					public void onError(Throwable e) {

					}

					@Override
					public void onComplete() {

					}
				});
	}

	private void testExchangeThread() {
		Observable.just(1, 2, 3)
				.map(new Function<Integer, Integer>() {
					@Override
					public Integer apply(Integer integer) throws Exception {
						HiLogger.i(TAG, "map==> %s", integer);
						return integer;
					}
				})
				.subscribe(new Observer<Integer>() {
					@Override
					public void onSubscribe(Disposable d) {

					}

					@Override
					public void onNext(Integer integer) {
						HiLogger.i(TAG, "onNext==> %s", integer);
					}

					@Override
					public void onError(Throwable e) {

					}

					@Override
					public void onComplete() {

					}
				});
	}

}

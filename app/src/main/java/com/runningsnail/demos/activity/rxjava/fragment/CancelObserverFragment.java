package com.runningsnail.demos.activity.rxjava.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.runningsnail.demos.MainHandler;
import com.runningsnail.demos.R;
import com.runningsnail.demos.base.BaseDemoFragment;
import com.runningsnail.demos.common.utils.HiLogger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CancelObserverFragment extends BaseDemoFragment {
	private static final String TAG = "CancelObserverFragment";
	@BindView(R.id.btn_cancel_way_one)
	Button btnCancelOne;
	@BindView(R.id.btn_cancel_way_two)
	Button btnCancelTwo;
	@BindView(R.id.btn_cancel_way_three)
	Button btnCancelThree;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_cancel_layout, container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	@OnClick({R.id.btn_cancel_way_one, R.id.btn_cancel_way_two, R.id.btn_cancel_way_three})
	public void onClick(View view) {
		int id = view.getId();
		switch (id) {
			case R.id.btn_cancel_way_one:
				cancelWayOne();
				break;
			case R.id.btn_cancel_way_two:
				cancelWayTwo();
				break;
			case R.id.btn_cancel_way_three:
				cancelWayThree();
				break;
			default:
				break;
		}
	}

	/**
	 * 继承 DisposableObserver 进行取消
	 */
	private void cancelWayThree() {
		HiLogger.i(TAG, "cancelWayThree");
		DisposableObserver<Integer> disposableObserver = new DisposableObserver<Integer>() {

			@Override
			public void onNext(@io.reactivex.annotations.NonNull Integer integer) {
				HiLogger.i(TAG, "onNext: %s", integer);
			}

			@Override
			public void onError(@io.reactivex.annotations.NonNull Throwable e) {
				HiLogger.i(TAG, "onError");
			}

			@Override
			public void onComplete() {
				HiLogger.i(TAG, "onComplete");
			}
		};
		Observable.just(1)
				.subscribeOn(Schedulers.io())
				.map(new Function<Integer, Integer>() {
					@Override
					public Integer apply(@io.reactivex.annotations.NonNull Integer integer) throws Exception {
						HiLogger.i(TAG, "map:==> %s", integer);
						Thread.sleep(3000);
						return integer;
					}
				})
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(disposableObserver);
		MainHandler.getInstance().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (!disposableObserver.isDisposed()) {
					HiLogger.i(TAG, "dispose");
					disposableObserver.dispose();
				}
			}
		}, 1000);
	}

	/**
	 * subscribeOn不直接返回一个disposable对象进行取消
	 */
	private void cancelWayTwo() {
		HiLogger.i(TAG, "cancelWayTwo");
		final Disposable[] disposable = {null};
		Observable.just(1)
				.subscribeOn(Schedulers.io())
				.map(new Function<Integer, Integer>() {
					@Override
					public Integer apply(@io.reactivex.annotations.NonNull Integer integer) throws Exception {
						HiLogger.i(TAG, "map:==> %s", integer);
						Thread.sleep(3000);
						return integer;
					}
				})
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Observer<Integer>() {
					@Override
					public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
						HiLogger.i(TAG, "onSubscribe");
						disposable[0] = d;
					}

					@Override
					public void onNext(@io.reactivex.annotations.NonNull Integer integer) {
						HiLogger.i(TAG, "onNext: %s", integer);
					}

					@Override
					public void onError(@io.reactivex.annotations.NonNull Throwable e) {
						HiLogger.i(TAG, "onError");
					}

					@Override
					public void onComplete() {
						HiLogger.i(TAG, "onComplete");
					}
				});
		MainHandler.getInstance().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (disposable[0] != null && !disposable[0].isDisposed()) {
					HiLogger.i(TAG, "dispose");
					disposable[0].dispose();
				}
			}
		}, 1000);
	}

	/**
	 * subscribeOn直接返回一个disposable对象进行取消
	 */
	private void cancelWayOne() {
		Disposable disposable = Observable.just(1)
				.subscribeOn(Schedulers.io())
				.map(new Function<Integer, Integer>() {
					@Override
					public Integer apply(@io.reactivex.annotations.NonNull Integer integer) throws Exception {
						HiLogger.i(TAG, "map:==> %s", integer);
						Thread.sleep(3000);
						return integer;
					}
				})
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<Integer>() {
					@Override
					public void accept(Integer integer) throws Exception {
						HiLogger.i(TAG, "onNext==>: %s", integer);
					}
				}, new Consumer<Throwable>() {
					@Override
					public void accept(Throwable throwable) throws Exception {
						HiLogger.i(TAG, "onError==>");
					}
				}, new Action() {
					@Override
					public void run() throws Exception {
						HiLogger.i(TAG, "onComplete==>");
					}
				});
		MainHandler.getInstance().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (!disposable.isDisposed()) {
					HiLogger.i(TAG, "dispose");
					disposable.dispose();
				}
			}
		}, 1000);
	}
}

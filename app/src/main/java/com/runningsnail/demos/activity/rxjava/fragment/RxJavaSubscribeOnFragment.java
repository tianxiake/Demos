package com.runningsnail.demos.activity.rxjava.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author yongjie created on 2021/1/14.
 */
public class RxJavaSubscribeOnFragment extends Fragment {
	private static final String TAG = "RxJavaSubscribeOnFragment";

	@BindView(R.id.btn_test_exchange_thread)
	Button testExchangeThread;
	@BindView(R.id.btn_test_invoke_twice)
	Button testInvokeTwice;
	@BindView(R.id.btn_test_special_do_on_subscribe)
	Button testDoOnSubscribe;
	@BindView(R.id.btn_test_special_do_on_subscribe2)
	Button testDoOnSubscribe2;
	@BindView(R.id.btn_much_observable_subscribe)
	Button testCustomObservable;


	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_subscribe_on_layout, container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	@OnClick({R.id.btn_test_invoke_twice,
			R.id.btn_test_exchange_thread,
			R.id.btn_test_special_do_on_subscribe,
			R.id.btn_test_special_do_on_subscribe2,
			R.id.btn_much_observable_subscribe})
	public void onClick(View view) {
		int id = view.getId();
		switch (id) {
			case R.id.btn_test_invoke_twice:
				testInvokeTwice();
				break;
			case R.id.btn_test_exchange_thread:
				testExchangeThread();
				break;
			case R.id.btn_test_special_do_on_subscribe:
				testDoOnSubscribe();
				break;
			case R.id.btn_test_special_do_on_subscribe2:
				testDoOnSubscribe2();
				break;
			case R.id.btn_much_observable_subscribe:
				testCustomObservable();
				break;
			default:
				break;
		}

	}

	/**
	 * 自定义多个Observable,每个Observable都调用subscribeOn和observerOn,最后最外层也调用
	 * subscribeOn和observerOn,看线程执行情况
	 */
	private void testCustomObservable() {


		Observable<Integer> testOneObservable = Observable.just(1).map(new Function<Integer, Integer>() {
			@Override
			public Integer apply(@io.reactivex.annotations.NonNull Integer integer) throws Exception {
				HiLogger.i(TAG, "第一个==> %s:", integer);
				return integer;
			}
		});

		Observable<Integer> testTwoObservable = Observable.just(2).subscribeOn(Schedulers.computation()).map(new Function<Integer, Integer>() {
			@Override
			public Integer apply(@io.reactivex.annotations.NonNull Integer integer) throws Exception {
				HiLogger.i(TAG, "第二个==> %s:", integer);
				return integer;
			}
		});

		Observable.concatArray(testOneObservable, testTwoObservable)
				.subscribeOn(Schedulers.newThread())
				.subscribe(new Observer<Integer>() {
					@Override
					public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

					}

					@Override
					public void onNext(@io.reactivex.annotations.NonNull Integer integer) {
						HiLogger.i(TAG, "最终结果: %s", integer);
					}

					@Override
					public void onError(@io.reactivex.annotations.NonNull Throwable e) {

					}

					@Override
					public void onComplete() {

					}
				});

	}

	/**
	 * 在doOnSubscribe之前调用subscribeOn看看执行的线程情况
	 */
	private void testDoOnSubscribe2() {
		HiLogger.i(TAG, "testDoOnSubscribe2");
		Observable.just(1, 2, 3)
				.subscribeOn(Schedulers.io())
				.map(new Function<Integer, Integer>() {
					@Override
					public Integer apply(Integer integer) throws Exception {
						HiLogger.i(TAG, "map==> %s", integer);
						return integer;
					}
				})
				.doOnSubscribe(new Consumer<Disposable>() {
					@Override
					public void accept(Disposable disposable) throws Exception {
						HiLogger.i(TAG, "doOnSubscribe==>");
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

	/**
	 * 在在doOnSubscribe之后再次调用subscribeOn看看执行的线程情况
	 */
	private void testDoOnSubscribe() {
		HiLogger.i(TAG, "testDoOnSubscribe");
		Observable.just(1, 2, 3)
				.subscribeOn(Schedulers.io())
				.map(new Function<Integer, Integer>() {
					@Override
					public Integer apply(Integer integer) throws Exception {
						HiLogger.i(TAG, "map==> %s", integer);
						return integer;
					}
				})
				.doOnSubscribe(new Consumer<Disposable>() {
					@Override
					public void accept(Disposable disposable) throws Exception {
						HiLogger.i(TAG, "doOnSubscribe==>");
					}
				})
				.subscribeOn(Schedulers.computation())
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

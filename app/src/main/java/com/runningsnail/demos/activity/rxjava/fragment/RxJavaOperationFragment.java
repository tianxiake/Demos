package com.runningsnail.demos.activity.rxjava.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.runningsnail.demos.MainHandler;
import com.runningsnail.demos.R;
import com.runningsnail.demos.activity.rxjava.entity.AuthDetail;
import com.runningsnail.demos.activity.rxjava.entity.PlayUrlContext;
import com.runningsnail.demos.activity.rxjava.entity.VodDetail;
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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaOperationFragment extends BaseDemoFragment {
	private static final String TAG = "RxJavaOperationFragment";
	@BindView(R.id.btn_rxjava_custom_observable)
	Button btnCustomObservable;
	@BindView(R.id.btn_rxjava_flat_map)
	Button btnFlatMap;
	@BindView(R.id.btn_concat_map_operation)
	Button btnConcatMap;
	private Context context;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_operation_layout, container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		context = this.getContext();
	}

	@OnClick({R.id.btn_concat_map_operation})
	public void onClick(View view) {
		int id = view.getId();
		switch (id) {
			case R.id.btn_concat_map_operation:
				concatMapOperation();
				break;
			case R.id.btn_rxjava_custom_observable:
				testCustomObservable();
				break;
			case R.id.btn_rxjava_flat_map:
				testFlatMap();
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

	/**
	 * 测试复杂的FlatMap
	 * 需求：
	 * PlayUrlContext
	 * 1、先查询详情页返回VodDetail
	 * 2、鉴权 AuthDetail
	 */
	private void testFlatMap() {
		final Disposable[] disposable = {null};
		Observable.just(new PlayUrlContext())
				.flatMap(new Function<PlayUrlContext, ObservableSource<PlayUrlContext>>() {

					@Override
					public ObservableSource<PlayUrlContext> apply(@io.reactivex.annotations.NonNull PlayUrlContext playUrlContext) throws Exception {
						return Observable.create(new ObservableOnSubscribe<PlayUrlContext>() {
							@Override
							public void subscribe(@io.reactivex.annotations.NonNull ObservableEmitter<PlayUrlContext> emitter) throws Exception {
								new Thread() {
									@Override
									public void run() {
										super.run();
										HiLogger.i(TAG, "VodDetail");
										VodDetail vodDetail = new VodDetail();
										vodDetail.setCode("12321444124");
										playUrlContext.setVodDetail(vodDetail);
										emitter.onNext(playUrlContext);
										emitter.onComplete();
									}
								}.start();
							}
						});

					}
				})
				.flatMap(new Function<PlayUrlContext, ObservableSource<PlayUrlContext>>() {
					@Override
					public ObservableSource<PlayUrlContext> apply(@io.reactivex.annotations.NonNull PlayUrlContext playUrlContext) throws Exception {
						return Observable.create(new ObservableOnSubscribe<PlayUrlContext>() {
							@Override
							public void subscribe(@io.reactivex.annotations.NonNull ObservableEmitter<PlayUrlContext> emitter) throws Exception {
								new Thread() {
									@Override
									public void run() {
										super.run();
										HiLogger.i(TAG, "authDetail");
										AuthDetail authDetail = new AuthDetail();
										authDetail.setResult("鉴权成功");
										playUrlContext.setAuthDetail(authDetail);
										emitter.onNext(playUrlContext);
//										try {
//											Thread.sleep(3000);
//										} catch (InterruptedException e) {
//											e.printStackTrace();
//										}
										emitter.onError(new RuntimeException());
									}
								}.start();
							}
						});
					}
				})
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Observer<PlayUrlContext>() {
					@Override
					public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
						disposable[0] = d;
					}

					@Override
					public void onNext(@io.reactivex.annotations.NonNull PlayUrlContext playUrlContext) {
						HiLogger.i(TAG, "onNext: %s", playUrlContext);
					}

					@Override
					public void onError(@io.reactivex.annotations.NonNull Throwable e) {
						HiLogger.i(TAG, "onError");
					}

					@Override
					public void onComplete() {
						HiLogger.i(TAG, "onComplete:");
					}
				});


		MainHandler.getInstance().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (!disposable[0].isDisposed()) {
					disposable[0].dispose();
				}
			}
		}, 1000);
	}

	/**
	 * 手动创建Observable,手动去发事件，灵活性非常的高
	 * 可以结合其他的网络请求去处理然后在发生出去
	 */
	private void testCustomObservable() {
		Observable.create(new ObservableOnSubscribe<Bitmap>() {
			@Override
			public void subscribe(@io.reactivex.annotations.NonNull ObservableEmitter<Bitmap> emitter) throws Exception {
				HiLogger.i(TAG, "subscribe");
				Glide.with(context).asBitmap().load("https://seopic.699pic.com/photo/40006/0637.jpg_wh1200.jpg")
						.into(new CustomTarget<Bitmap>() {
							@Override
							public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
								HiLogger.i(TAG, "onResourceReady");
								emitter.onNext(resource);
								emitter.onComplete();
							}

							@Override
							public void onLoadCleared(@Nullable Drawable placeholder) {
								HiLogger.i(TAG, "onLoadCleared");
								emitter.onError(null);
							}
						});
			}
		}).subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Observer<Bitmap>() {
					@Override
					public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
						HiLogger.i(TAG, "onSubscribe");
					}

					@Override
					public void onNext(@io.reactivex.annotations.NonNull Bitmap bitmap) {
						HiLogger.i(TAG, "onNext");
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
	}

}

package com.runningsnail.demos.activity.rxjava;

import android.content.Intent;
import android.icu.text.MessageFormat;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava测试Activity
 */
public class RxJavaOneActivity extends AppCompatActivity {

	public static final String TAG = "RxJavaOneActivity";
	@BindView(R.id.btn_one)
	Button button;


	private int testValue = 1;

	private SimpleClass simple = new SimpleClass();

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rx_java_one);
		ButterKnife.bind(this);
	}

	@OnClick(R.id.btn_one)
	public void onClick(View view) {
		HiLogger.d(TAG, "onClick: view:" + view);
		int id = view.getId();
		switch (id) {
			case R.id.btn_one:
				simpleChangeThread();
				break;
			default:
				break;
		}

	}

	private void simpleChangeThread() {
		Observable.just(simple).subscribeOn(Schedulers.io())
				.doOnNext(new Consumer<SimpleClass>() {
					@Override
					public void accept(SimpleClass simpleClass) throws Exception {
						simpleClass.a = 2;
						HiLogger.d(TAG, "simple:" + simpleClass);
					}
				}).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<SimpleClass>() {
			@Override
			public void accept(SimpleClass simpleClass) throws Exception {
				HiLogger.d(TAG, "simple:" + simple);
			}
		});
	}

	public static class SimpleClass {
		public int a = 1;
		public String b = "simple";

		@Override
		public String toString() {
			return "SimpleClass{" +
					"a=" + a +
					", b='" + b + '\'' +
					'}';
		}
	}
}

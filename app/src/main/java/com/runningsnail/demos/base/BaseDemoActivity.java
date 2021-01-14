package com.runningsnail.demos.base;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yongjie created on 2021/1/14.
 */
public abstract class BaseDemoActivity extends AppCompatActivity implements OperationInf {

	public static final String TAG = "BaseDemoActivity";
	private FragmentManager fragmentManager;

	@BindView(R.id.btn_go_home)
	Button goHome;
	private Class<? extends Fragment> fragmentClass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base_layout);
		ButterKnife.bind(this);
		fragmentManager = getSupportFragmentManager();
		goHome.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showMainFragment();
			}
		});

		fragmentClass = getMainFragment();
		showMainFragment();
	}

	protected abstract Class<? extends Fragment> getMainFragment();


	private void showMainFragment() {
		try {
			Fragment fragment = fragmentClass.newInstance();
			replaceFragment(fragment);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Override
	public void replaceFragment(Fragment fragment) {
		requestHomeFocus();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.fl_fragment_container, fragment);
		fragmentTransaction.commit();
	}

	@Override
	public void requestHomeFocus() {
		goHome.requestFocus();
	}
}

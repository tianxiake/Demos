package com.runningsnail.demos.activity.tv;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.runningsnail.demos.R;
import com.runningsnail.demos.activity.tv.fragment.ClipCaseEightFragment;
import com.runningsnail.demos.activity.tv.fragment.ClipCaseFiveFragment;
import com.runningsnail.demos.activity.tv.fragment.ClipCaseFourFragment;
import com.runningsnail.demos.activity.tv.fragment.ClipCaseOneFragment;
import com.runningsnail.demos.activity.tv.fragment.ClipCaseSevenFragment;
import com.runningsnail.demos.activity.tv.fragment.ClipCaseSixFragment;
import com.runningsnail.demos.activity.tv.fragment.ClipCaseThreeFragment;
import com.runningsnail.demos.activity.tv.fragment.ClipCaseTwoFragment;
import com.runningsnail.demos.common.utils.HiLogger;

public class ClipPaddingAndChildTestActivity extends AppCompatActivity implements View.OnFocusChangeListener {
	private static final String TAG = "ClipPaddingAndChildTestActivity";
	Button buttonOne;
	Button buttonTwo;
	Button buttonThree;
	Button buttonFour;
	Button buttonFive;
	Button buttonSix;
	Button buttonSeven;
	Button buttonEight;
	FragmentManager fragmentManager;
	private View lastFocusView;
	private ViewGroup menuContainer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clip_padding_and_child_test);
		buttonOne = findViewById(R.id.btn_one);
		buttonTwo = findViewById(R.id.btn_two);
		buttonThree = findViewById(R.id.btn_three);
		buttonFour = findViewById(R.id.btn_four);
		buttonFive = findViewById(R.id.btn_five);
		buttonSix = findViewById(R.id.btn_six);
		buttonSeven = findViewById(R.id.btn_seven);
		buttonEight = findViewById(R.id.btn_eight);
		menuContainer = findViewById(R.id.menu_container);
		setOnFocusListener(buttonOne);
		setOnFocusListener(buttonTwo);
		setOnFocusListener(buttonThree);
		setOnFocusListener(buttonFour);
		setOnFocusListener(buttonFive);
		setOnFocusListener(buttonSix);
		setOnFocusListener(buttonSeven);
		setOnFocusListener(buttonEight);
		fragmentManager = getSupportFragmentManager();
	}

	private void setOnFocusListener(View view) {
		view.setOnFocusChangeListener(this);
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus) {
			int id = v.getId();
			switch (id) {
				case R.id.btn_one:
					replaceFragment(new ClipCaseOneFragment());
					break;
				case R.id.btn_two:
					replaceFragment(new ClipCaseTwoFragment());
					break;
				case R.id.btn_three:
					replaceFragment(new ClipCaseThreeFragment());
					break;
				case R.id.btn_four:
					replaceFragment(new ClipCaseFourFragment());
					break;
				case R.id.btn_five:
					replaceFragment(new ClipCaseFiveFragment());
					break;
				case R.id.btn_six:
					replaceFragment(new ClipCaseSixFragment());
					break;
				case R.id.btn_seven:
					replaceFragment(new ClipCaseSevenFragment());
					break;
				case R.id.btn_eight:
					replaceFragment(new ClipCaseEightFragment());
					break;

			}
		}
	}

	private void replaceFragment(Fragment fragment) {
		HiLogger.d(TAG, "replaceFragment %s", fragment);
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.fragment_container, fragment);
		fragmentTransaction.commitNow();
	}
}

package com.runningsnail.demos.activity.widget;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.widget.tv.SelectionsViewGroup;
import com.runningsnail.demos.widget.tv.SimpleDataLoader;
import com.runningsnail.demos.widget.tv.TestOneSelectionView;
import com.runningsnail.demos.widget.tv.TestSelectionView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectionActivity extends AppCompatActivity {

	@BindView(R.id.activity_selection_view)
	SelectionsViewGroup activitySelectionView;
	@BindView(R.id.btn_remove_selection)
	Button btnRemoveSelection;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selction);
		ButterKnife.bind(this);

		TestSelectionView recommend = createTestSelectionView("相关推荐", "1");
		activitySelectionView.addSelectionsView(recommend);
		activitySelectionView.addSelectionsView(createTestOneSelectionView("精彩推荐", "2"));
		activitySelectionView.addSelectionsView(createTestSelectionView("人气推荐", "3"));
		activitySelectionView.addSelectionsView(createTestOneSelectionView("经典推荐", "4"));
		activitySelectionView.show();

		btnRemoveSelection.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				activitySelectionView.removeSelectionsView("1");
			}
		});
	}

	private TestSelectionView createTestSelectionView(String title, String type) {
		TestSelectionView testSelectionView = new TestSelectionView(this);
		testSelectionView.setTitleText(title);
		testSelectionView.setType(type);
		testSelectionView.bindData(new SimpleDataLoader() {
			@Override
			public String getTitle() {
				return "哈哈哈1";
			}
		});
		testSelectionView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		return testSelectionView;
	}

	private TestSelectionView createTestOneSelectionView(String title, String type) {
		TestOneSelectionView testSelectionView = new TestOneSelectionView(this);
		testSelectionView.setTitleText(title);
		testSelectionView.bindData(new SimpleDataLoader() {
			@Override
			public String getTitle() {
				return "哈哈哈2";
			}
		});
		testSelectionView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		return testSelectionView;
	}
}
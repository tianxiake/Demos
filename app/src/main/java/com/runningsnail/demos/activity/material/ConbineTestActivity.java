package com.runningsnail.demos.activity.material;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.runningsnail.demos.R;

import java.util.ArrayList;
import java.util.List;

public class ConbineTestActivity extends AppCompatActivity {

	private RecyclerView mRecyclerView;
	private CommonRecyclerAdapter<String> mAdapter;
	private List<String> mStringList;
	private Toolbar mToolBar;
	private Toolbar mToolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conbine_test);

		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		initToolBar(mToolbar, false, "");

		mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());

		mStringList = new ArrayList<>();

		for (int i = 0; i < 100; i++) {
			mStringList.add("测试："+i);
		}

		mAdapter = new CommonRecyclerAdapter<String>(this,mStringList,R.layout.layout_item) {
			@Override
			public void convert(CommonRecyclerHolder holder, String item, int position, boolean isScrolling) {
				holder.setText(R.id.item_text,mStringList.get(position));
			}
		};

		mRecyclerView.setAdapter(mAdapter);

		mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
	}

	/** 初始化 Toolbar */
	public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
		toolbar.setTitle(title);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
	}
}

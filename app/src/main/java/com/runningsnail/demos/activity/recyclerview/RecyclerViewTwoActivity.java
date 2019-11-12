package com.runningsnail.demos.activity.recyclerview;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.runningsnail.demos.common.utils.AnimUtil;
import com.runningsnail.demos.common.utils.HiLogger;
import com.runningsnail.demos.adapter.BaseAdapter;
import com.runningsnail.demos.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewTwoActivity extends AppCompatActivity {


	public static final String TAG = "RecyclerViewTwoActivity";
	@BindView(R.id.rv_content)
	RecyclerView recyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recycler_view_one);
		ButterKnife.bind(this);

		List<String> content = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			content.add("第" + i + "数据");
		}

		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
		recyclerView.setClipToPadding(false);
		recyclerView.setClipChildren(false);
		recyclerView.setLayoutManager(linearLayoutManager);
		recyclerView.setAdapter(new MyAdapter(content));

	}


	public class MyAdapter extends BaseAdapter<String> {

		public MyAdapter(List<String> dataList) {
			super(dataList);
		}

		@Override
		public int getCustomViewType(int position) {
			return 0;
		}

		@Override
		public int getLayoutId(int viewType) {
			return R.layout.layout_item_one;
		}

		@Override
		public void convert(final BaseViewHolder holder, final String data, int position, int viewType) {
			HiLogger.d(TAG, "convert position:" + position);
			holder.itemView.setFocusable(true);
			ViewGroup viewGroup = (ViewGroup) holder.itemView;
			viewGroup.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

			final TextView textView = holder.itemView.findViewById(R.id.tv_text);
			textView.setText(data);
			holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
//					if (hasFocus) {
//						textView.setText("onFocus");
//					} else {
//						textView.setText(data);
//					}
					AnimUtil.scaleView(holder.itemView, hasFocus);
				}
			});
		}

	}
}

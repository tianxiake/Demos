package com.runningsnail.demos.activity.recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.runningsnail.demos.common.utils.AnimUtil;
import com.runningsnail.demos.common.utils.HiLogger;
import com.runningsnail.demos.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewOneActivity extends AppCompatActivity {


	public static final String TAG = "RecyclerViewOneActivity";
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
		recyclerView.setLayoutManager(linearLayoutManager);
		recyclerView.setAdapter(new MyAdapter(content));

	}


	public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
		List<String> dataLists;

		public MyAdapter(List<String> dataLists) {
			this.dataLists = dataLists;
		}

		@NonNull
		@Override
		public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			HiLogger.d(TAG, "onCreateViewHolder");
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_one, parent, false);
			return new ViewHolder(view);
		}

		@Override
		public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
			HiLogger.d(TAG, "onBindViewHolder position:" + position);
			holder.itemView.setFocusable(true);
			ViewGroup viewGroup = (ViewGroup) holder.itemView;
			viewGroup.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

			final TextView textView = holder.itemView.findViewById(R.id.tv_text);
			textView.setText(dataLists.get(position));
			holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
//					if (hasFocus) {
//						textView.setText("onFocus");
//					} else {
//						textView.setText(dataLists.get(position));
//					}
					AnimUtil.scaleView(v, hasFocus);
				}
			});
		}

		@Override
		public int getItemCount() {
			return dataLists.size();
		}

		class ViewHolder extends RecyclerView.ViewHolder {

			public ViewHolder(View itemView) {
				super(itemView);
			}
		}
	}
}

package com.runningsnail.demos.activity.recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.AnimUtil;
import com.runningsnail.demos.common.utils.HiLogger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 网格两行布局
 */
public class RecyclerViewFiveActivity extends AppCompatActivity {


	public static final String TAG = "RecyclerViewFiveActivity";
	@BindView(R.id.rv_content)
	RecyclerView recyclerView;
	@BindView(R.id.btn_span_index)
	Button button;
	private GridLayoutManager linearLayoutManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recycler_view_five);
		ButterKnife.bind(this);

		List<String> content = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			content.add("第" + i + "数据");
		}

		linearLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.HORIZONTAL, false);
		recyclerView.setLayoutManager(linearLayoutManager);
		recyclerView.setAdapter(new MyAdapter(content));

	}

	@OnClick(R.id.btn_span_index)
	public void onClick(View view) {
		int spanIndex = linearLayoutManager.getSpanSizeLookup().getSpanIndex(11, linearLayoutManager.getSpanCount());
		int spanGroupIndex = linearLayoutManager.getSpanSizeLookup().getSpanGroupIndex(11, linearLayoutManager.getSpanCount());
		HiLogger.d(TAG, "spanIndex:" + spanIndex + ",spanGroupIndex:" + spanGroupIndex);
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

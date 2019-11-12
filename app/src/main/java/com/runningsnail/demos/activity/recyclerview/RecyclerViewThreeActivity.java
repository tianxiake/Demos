package com.runningsnail.demos.activity.recyclerview;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.runningsnail.demos.R;
import com.runningsnail.demos.SimpleSpaceItemDecoration;
import com.runningsnail.demos.common.utils.HiLogger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecyclerViewThreeActivity extends AppCompatActivity {
	public static final String TAG = "RecyclerViewThreeActivity";
	@BindView(R.id.rv_content)
	RecyclerView recyclerView;

	@BindView(R.id.btn_test)
	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recycler_view_three);
		ButterKnife.bind(this);

		recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
		recyclerView.addItemDecoration(new SimpleSpaceItemDecoration(0, 0, 30, 0));
		recyclerView.setAdapter(new MyAdapter());
	}

	@OnClick(R.id.btn_test)
	public void onClick(View view) {
		Rect rect = new Rect();
		recyclerView.getGlobalVisibleRect(rect);
		HiLogger.d(TAG, rect.toString());
		LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
		int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
		HiLogger.d(TAG, "firstVisibleItemPosition:" + firstVisibleItemPosition);
	}


	public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {


		@NonNull
		@Override
		public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			TextView textView = new TextView(parent.getContext());
			textView.setLayoutParams(new ViewGroup.LayoutParams(100, 200));
			textView.setGravity(Gravity.CENTER);
			textView.setBackgroundColor(Color.BLUE);
			textView.setTextColor(Color.RED);
			return new MyHolder(textView);
		}

		@Override
		public void onBindViewHolder(@NonNull MyHolder holder, int position) {
			final TextView textView = (TextView) holder.itemView;
			textView.setFocusable(true);
			textView.setText(position + "");

			holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if (hasFocus) {
						v.setBackgroundColor(Color.GRAY);
					} else {
						v.setBackgroundColor(Color.BLUE);
					}

				}
			});

		}

		@Override
		public int getItemCount() {
			return 100;
		}

		public class MyHolder extends RecyclerView.ViewHolder {


			public MyHolder(View itemView) {
				super(itemView);
			}
		}
	}
}

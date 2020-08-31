package com.runningsnail.demos.activity.tv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.runningsnail.demos.CustomViewHolder;
import com.runningsnail.demos.R;
import com.runningsnail.demos.SimpleSpaceItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewActivity extends AppCompatActivity {

	@BindView(R.id.rv_list)
	RecyclerView rvList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recycler_view);
		ButterKnife.bind(this);

		rvList.setLayoutManager(new GridLayoutManager(this, 3));
		rvList.addItemDecoration(new SimpleSpaceItemDecoration(30, 0, 30, 0, false));
		rvList.setAdapter(new RecyclerView.Adapter() {
			@NonNull
			@Override
			public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
				View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_item, parent, false);
				return new CustomViewHolder(inflate);
			}

			@Override
			public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
				CustomViewHolder customViewHolder = (CustomViewHolder) holder;
				TextView textView = (TextView) customViewHolder.itemView;
				textView.setText(position + "");
			}

			@Override
			public int getItemCount() {
				return 20;
			}
		});
	}
}

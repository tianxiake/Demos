package com.runningsnail.demos.tvwidget.powerfulrecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapter<E> extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder> {

	/**
	 * 适配器的数据集合
	 */
	private List<E> dataList = new ArrayList<>();

	public BaseAdapter(List<E> dataList) {
		this.dataList = dataList;
	}

	@NonNull
	@Override
	public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return null;
	}

	@Override
	public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return dataList.size();
	}

	public class BaseViewHolder extends RecyclerView.ViewHolder {

		public BaseViewHolder(View itemView) {
			super(itemView);
		}
	}
}

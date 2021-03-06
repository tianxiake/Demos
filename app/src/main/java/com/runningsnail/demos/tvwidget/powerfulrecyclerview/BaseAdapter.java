package com.runningsnail.demos.tvwidget.powerfulrecyclerview;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<E> extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder> {

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
	public int getItemCount() {
		return dataList.size();
	}

	public class BaseViewHolder extends RecyclerView.ViewHolder {

		public BaseViewHolder(View itemView) {
			super(itemView);
		}
	}
}

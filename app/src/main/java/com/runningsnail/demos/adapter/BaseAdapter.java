package com.runningsnail.demos.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.runningsnail.demos.OnItemClickListener;
import com.runningsnail.demos.OnItemFocusChangeListener;
import com.runningsnail.demos.common.utils.HiLogger;

import java.util.List;

/**
 * @author liuyongjie create on 2018/12/11.
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder> {

	private static final String TAG = "BaseAdapter";

	private LinearLayout headerViews;

	private final int TYPE_HEADER_VIEW = -0xff;

	private List<T> dataList;

	/**
	 * 对整个itemView生效，如果itemView是一个复合View,需要其中子View获取点击事件,
	 * 请在{@link #convert(BaseViewHolder, Object, inDt, int)}方法中去实现
	 */
	private OnItemClickListener onItemClickListener;
	/**
	 * 对整个itemView生效，如果itemView是一个复合View,要其中子View获取焦点事件，
	 * 请在{@link #convert(BaseViewHolder, Object, int, int)}方法中去实现
	 */
	private OnItemFocusChangeListener onItemFocusChangeListener;

	public BaseAdapter(List<T> dataList) {
		this.dataList = dataList;
	}


	@Override
	public int getItemViewType(int position) {
		Log.d(TAG, "getItemViewType: position:" + position);
		if (position == 0 && headerViews != null) {
			return TYPE_HEADER_VIEW;
		}
		return getCustomViewType(position);
	}

	/**
	 * 自定义的模板类型
	 *
	 * @param position
	 * @return
	 */
	public abstract int getCustomViewType(int position);

	/**
	 * 根据ViewType提供对应的布局id
	 *
	 * @param viewType
	 * @return
	 */
	public abstract int getLayoutId(int viewType);

	public boolean headViewIsEmpty() {
		return headerViews == null;
	}

	@NonNull
	@Override
	public BaseAdapter.BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		BaseViewHolder baseViewHolder;
		if (viewType == TYPE_HEADER_VIEW) {
			baseViewHolder = new BaseViewHolder(headerViews);
		} else {
			baseViewHolder = BaseViewHolder.get(parent, getLayoutId(viewType));
		}
		return baseViewHolder;
	}

	public List<T> getDataList() {
		return dataList;
	}

	@Override
	public void onBindViewHolder(@NonNull BaseAdapter.BaseViewHolder holder, int position) {
		bindListener(holder.itemView, position);
		if (headerViews == null) {
			convert(holder, dataList.get(position), position, getItemViewType(position));
		} else {
			if (position != 0) {
				convert(holder, dataList.get(position - 1), position, getItemViewType(position));
			}
		}
	}

	private void bindListener(View itemView, final int position) {
		if (onItemClickListener != null) {
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (headerViews == null) {
						onItemClickListener.onItemClick(v, dataList.get(position), position);
					} else {
						onItemClickListener.onItemClick(v, dataList.get(position - 1), position);
					}
				}
			});
		}

		if (onItemFocusChangeListener != null) {
			itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					onItemFocusChangeListener.onFocusChange(v, hasFocus);
				}
			});
		}
	}

	/**
	 * 数据处理接口
	 *
	 * @param holder   holder对象
	 * @param data     选中的条目数据
	 * @param position 选中的位置
	 */
	public abstract void convert(BaseViewHolder holder, T data, int position, int viewType);

	@Override
	public int getItemCount() {
		HiLogger.d(TAG, "getItemCount");
		int size = 0;
		if (headerViews != null) {
			size = 1;
		}
		if (dataList != null) {
			size += dataList.size();
		}
		return size;
	}

	/**
	 * 添加头部View,头部View中子View的点击事件和焦点事件
	 * 内部无法提供,需要外部设置完成传递进来
	 */
	public void addHeaderView(View view) {
		//只支持垂直方向
		if (headerViews == null) {
			headerViews = new LinearLayout(view.getContext());
			headerViews.setOrientation(LinearLayout.VERTICAL);
			headerViews.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT));
		}
		headerViews.addView(view);
		//刷新数据
		notifyItemChanged(0);
	}

	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}

	public void setOnItemFocusChangeListener(OnItemFocusChangeListener onItemFocusChangeListener) {
		this.onItemFocusChangeListener = onItemFocusChangeListener;
	}

	public OnItemClickListener getOnItemClickListener() {
		return onItemClickListener;
	}

	public OnItemFocusChangeListener getOnItemFocusChangeListener() {
		return onItemFocusChangeListener;
	}

	public static class BaseViewHolder extends RecyclerView.ViewHolder {
		private SparseArray<View> mViews;
		public View itemView;

		public BaseViewHolder(View itemView) {
			super(itemView);
			this.itemView = itemView;
			mViews = new SparseArray<>();
		}

		public static BaseViewHolder get(ViewGroup parent, int layoutId) {
			View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
			return new BaseViewHolder(convertView);
		}

		/**
		 * 根据id去查找某个View对象
		 *
		 * @param id
		 * @return
		 */
		public View getView(int id) {
			View view = mViews.get(id);
			if (view == null) {
				view = itemView.findViewById(id);
				mViews.put(id, view);
			}
			return view;
		}
	}
}

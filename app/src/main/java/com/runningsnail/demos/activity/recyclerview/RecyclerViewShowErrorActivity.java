package com.runningsnail.demos.activity.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.runningsnail.demos.common.utils.AnimUtil;
import com.runningsnail.demos.adapter.BaseAdapter;
import com.runningsnail.demos.R;
import com.runningsnail.demos.RecommendBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewShowErrorActivity extends AppCompatActivity {

	public static final String TAG = "RecyclerViewShowErrorActivity";

	@BindView(R.id.rv_content)
	RecyclerView recyclerView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recycler_view_show_error);
		ButterKnife.bind(this);

		List<RecommendBean> recommendBeans = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			RecommendBean recommendBean = new RecommendBean();
			recommendBean.icon = "http://192.168.222.11:8080/chances/yunnan/monkey.jpg";
			recommendBean.title = "斗破苍穹" + i;
			recommendBean.subTitle = "斗破苍穹讲述了一个传奇少年打怪升级的故事";
			recommendBeans.add(recommendBean);
		}

		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
		recyclerView.setClipToPadding(false);
		recyclerView.setClipChildren(false);
		recyclerView.setLayoutManager(linearLayoutManager);
		recyclerView.setAdapter(new RecyclerViewShowErrorActivity.MyAdapter(recommendBeans));
	}


	public class MyAdapter extends BaseAdapter<RecommendBean> {

		public MyAdapter(List<RecommendBean> dataList) {
			super(dataList);
		}

		@Override
		public int getCustomViewType(int position) {
			return 0;
		}

		@Override
		public int getLayoutId(int viewType) {
			return R.layout.layout_item_show_error_item;
		}

		@Override
		public void convert(final BaseViewHolder holder, final RecommendBean data, int position, int viewType) {
			holder.itemView.setFocusable(true);
			ViewGroup viewGroup = (ViewGroup) holder.itemView;
			viewGroup.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

			ImageView icon = (ImageView) holder.getView(R.id.iv_item_icon);

			final TextView normalTitle = (TextView) holder.getView(R.id.tv_normal_title);
			TextView focusTitle = (TextView) holder.getView(R.id.tv_focus_title);
			TextView focusSubTitle = (TextView) holder.getView(R.id.tv_focus_sub_title);
			normalTitle.setText(data.title);
			focusTitle.setText(data.title);
			focusSubTitle.setText(data.subTitle);

			final LinearLayout focusContainer = (LinearLayout) holder.getView(R.id.ll_focus_container);
			holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if (hasFocus) {
						focusContainer.setVisibility(View.VISIBLE);
					} else {
						focusContainer.setVisibility(View.GONE);
					}
					CharSequence text = normalTitle.getText();
					AnimUtil.scaleView(v, hasFocus);
				}
			});
		}
	}
}

package com.runningsnail.demos.activity.tv;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.chances.base.common.ImageLoader;
import com.runningsnail.demos.R;
import com.runningsnail.demos.activity.tv.widget.CustomVideoView;
import com.runningsnail.demos.common.utils.HiLogger;

import org.greenrobot.eventbus.EventBus;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewVisibleActivity extends AppCompatActivity {
	private static final String TAG = "ViewVisibleActivity";
	@BindView(R.id.view_pager)
	ViewPager viewPager;
	int[] colors = new int[]{Color.RED, Color.WHITE, Color.BLACK, Color.BLUE, Color.GRAY, Color.GREEN, Color.YELLOW};
	Random random = new Random();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_visible);
		ButterKnife.bind(this);

		viewPager.setOffscreenPageLimit(1);
		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//				HiLogger.d(TAG, "onPageScrolled %s", position);
			}

			@Override
			public void onPageSelected(int position) {
				HiLogger.d(TAG, "onPageSelected %s", position);
				EventBus.getDefault().post(new PageSelectMessage());
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				HiLogger.d(TAG, "onPageScrollStateChanged %s", state);
			}
		});
		viewPager.setAdapter(new PagerAdapter() {
			@NonNull
			@Override
			public Object instantiateItem(@NonNull ViewGroup container, int position) {
				HiLogger.d(TAG, "instantiateItem position: %s", position);
				View view;
				if (position == 3) {
					RecyclerView recyclerView = new RecyclerView(container.getContext());
					recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
						@Override
						public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
							super.onScrolled(recyclerView, dx, dy);
							EventBus.getDefault().post(new PageSelectMessage());
						}
					});
					recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext(), RecyclerView.VERTICAL, false));
					recyclerView.setAdapter(new RecyclerView.Adapter() {
						@NonNull
						@Override
						public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
							if (viewType == 1) {
								FrameLayout frameLayout = new FrameLayout(container.getContext());
								frameLayout.setFocusable(true);
								frameLayout.setBackgroundColor(colors[random.nextInt(colors.length)]);
								frameLayout.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500));
								CustomVideoView customVideoView = new CustomVideoView(container.getContext());
								FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(300, 400);
								layoutParams.gravity = Gravity.CENTER;
								customVideoView.setLayoutParams(layoutParams);
								frameLayout.addView(customVideoView);
								return new MyViewHolder(frameLayout);
							}
							ImageView imageView = new ImageView(parent.getContext());
							imageView.setFocusable(true);
							imageView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
							imageView.setBackgroundColor(colors[random.nextInt(colors.length)]);
							return new MyViewHolder(imageView);
						}

						@Override
						public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
						}

						@Override
						public int getItemViewType(int position) {
							if (position == 0) {
								return 1;
							} else {
								return 2;
							}
						}

						@Override
						public int getItemCount() {
							return 10;
						}
					});
					recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
					view = recyclerView;
				} else {
					view = new AppCompatImageView(container.getContext());
					ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
					layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
					layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
					view.setLayoutParams(layoutParams);
					view.setBackgroundColor(colors[random.nextInt(colors.length)]);
				}
				container.addView(view);

				return view;
			}

			@Override
			public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
				HiLogger.d(TAG, "destroyItem position: %s", position);
				container.removeView((View) object);
			}

			@Override
			public int getCount() {
				return 10;
			}

			@Override
			public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
				return view == object;
			}
		});
	}

	public static class MyViewHolder extends RecyclerView.ViewHolder {

		public MyViewHolder(@NonNull View itemView) {
			super(itemView);
		}
	}

}

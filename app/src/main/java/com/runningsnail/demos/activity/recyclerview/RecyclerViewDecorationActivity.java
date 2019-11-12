package com.runningsnail.demos.activity.recyclerview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class RecyclerViewDecorationActivity extends AppCompatActivity {

    public static final String TAG = "RecyclerViewDecorationActivity";
    @BindView(R.id.rv_content)
    RecyclerView rvContent;

    private List<Item> list;

    private MyAdapter myAdapter;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_decoration);
        ButterKnife.bind(this);

        list = new ArrayList<>();

        list.add(new Item("time", "本周", ""));
        for (int i = 0; i < 15; i++) {
            list.add(new Item("item", "", ""));
        }

        list.add(new Item("time", "上周", ""));
        for (int i = 0; i < 7; i++) {
            list.add(new Item("item", "", ""));
        }

        list.add(new Item("time", "更早", ""));
        for (int i = 0; i < 30; i++) {
            list.add(new Item("item", "", ""));
        }


        initViews();
    }

    private void initViews() {
        final int spanCount = 2;
        myAdapter = new MyAdapter(list);
        gridLayoutManager = new GridLayoutManager(this, spanCount, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int result = 0;
                Item item = list.get(position);
                if ("time".equals(item.type)) {
                    result = spanCount;
                } else {
                    result = 1;
                }
                HiLogger.d(TAG, "getSpanSize result: %s, position: %s item: %s", result, position, item);
                return result;
            }
        });
        rvContent.addItemDecoration(new MyItemDecoration());
        rvContent.setLayoutManager(gridLayoutManager);
        rvContent.setAdapter(myAdapter);
    }


    public class MyItemDecoration extends RecyclerView.ItemDecoration {

        Paint paint = new Paint();

        private int startX;
        private int startY;

        private int stopX;
        private int stopY;

        public MyItemDecoration() {
            super();
            paint.setColor(Color.GRAY);
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeWidth(5);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);

            int childCount = parent.getChildCount();
            startX = 10;
            startY = 10;
            stopX = 10;
            for (int i = 0; i < childCount; i++) {
                View childAt = parent.getChildAt(i);
                stopY += childAt.getMeasuredHeight() + childAt.getPaddingTop() + childAt.getPaddingBottom();
            }
//
////            for (int i = 0; i < childCount; i++) {
////                View childAt = parent.getChildAt(i);
////                if (i == 0) {
////                    startX = childAt.getMeasuredWidth() / 2;
////                    startY = childAt.getMeasuredHeight() / 2;
////                } else {
////                    stopY += childAt.getMeasuredHeight();
////                }
////            }
////            stopX = startX;
            HiLogger.d(TAG, "startX:%s startY:%s  stopX:%s  stopY:%s  childCount:%s", startX, startY, stopX, stopY, childCount);
            c.drawLine(startX, startY, stopX, stopY, paint);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);

        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int childAdapterPosition = parent.getChildAdapterPosition(view);
            int itemViewType = myAdapter.getItemViewType(childAdapterPosition);
            if (itemViewType == R.layout.layout_item_time) {
                outRect.left = 0;
                outRect.right = 0;
                outRect.top = 0;
                outRect.bottom = 0;
            } else {
                outRect.left = 25;
                outRect.right = 25;
                outRect.top = 20;
                outRect.bottom = 20;
            }

        }
    }


    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private List<Item> dataLists = new ArrayList<>();

        public MyAdapter(List<Item> dataLists) {
            this.dataLists = dataLists;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            MyViewHolder myViewHolder;
            if (viewType == R.layout.layout_item_time) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_time, parent, false);
                myViewHolder = new MyViewHolder(view);
            } else {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_pic, parent, false);
                myViewHolder = new MyViewHolder(view);
            }
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Item item = dataLists.get(position);
            HiLogger.d(TAG, "position: %s, item: %s", position, item);
            int itemViewType = getItemViewType(position);
            if (itemViewType == R.layout.layout_item_time) {
                TextView textView = (TextView) holder.getView(R.id.tv_time);
                textView.setText(item.time);
            } else {
                holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        AnimUtil.scaleView(v, hasFocus);
                    }
                });
            }

        }

        @Override
        public int getItemViewType(int position) {
            if (dataLists.size() > 0) {
                Item item = dataLists.get(position);
                if ("time".equals(item.type)) {
                    return R.layout.layout_item_time;
                } else {
                    return R.layout.layout_item_pic;
                }
            }
            return super.getItemViewType(position);
        }

        @Override
        public int getItemCount() {
            return dataLists.size();
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private SparseArray<View> sparseArray = new SparseArray<>();

        public MyViewHolder(View itemView) {
            super(itemView);
        }

        public View getView(int viewId) {
            View view = sparseArray.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                sparseArray.put(viewId, view);
            }
            return view;
        }
    }


}

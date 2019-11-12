package com.runningsnail.demos.activity.tv.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.runningsnail.demos.R;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseSimpleListView extends FrameLayout {

    protected String TAG = "";

    private RecyclerView recyclerView;

    private ConfigOption configOption;
    private ContentAdapter contentAdapter;
    private GridLayoutManager gridLayoutManager;

    /**
     * 列的数量
     */
    private int spanCount = 1;

    protected FocusHandleListener focusHandleListener;
    protected ItemMessageListener itemMessageListener;

    private OnListItemClickListener onListItemClickListener;
    private OnListItemFocusChangeListener onListItemFocusChangeListener;


    public BaseSimpleListView(@NonNull Context context) {
        this(context, null);
    }

    public BaseSimpleListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseSimpleListView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    protected void init() {
        TAG = buildTag();
        initView();
    }

    /**
     * 构建日志TAG
     */
    protected String buildTag() {
        return this.getClass().getSimpleName();
    }

    /**
     * 初始化View
     */
    private void initView() {
        View view = LayoutInflater.from(this.getContext()).inflate(R.layout.layout_simple_list_content_view, null);
        recyclerView = view.findViewById(R.id.rv_content);
        gridLayoutManager = new GridLayoutManager(this.getContext(), spanCount, LinearLayout.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        this.addView(recyclerView);
    }

    /**
     * 收藏绑定数据
     *
     * @param listItems
     */
    public void bindData(ConfigOption configOption, List<ListItem> listItems) {
        this.configOption = configOption;
        gridLayoutManager.setSpanCount(configOption.columnsCount);
        contentAdapter = new ContentAdapter(listItems, configOption.itemWidth, configOption.itemHeight);
        int height = this.getMeasuredHeight();
        int width = this.getMeasuredWidth();
//        int topSpace = (height - configOption.itemHeight * configOption.linesCount)
//        recyclerView.addItemDecoration(new SpaceItemDecoration());
        recyclerView.setAdapter(contentAdapter);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 列表是否有数据显示
     *
     * @return false表示没有数据可以展示
     */
    public boolean hasChildViewCanShow() {
        return false;
    }

    /**
     * 设置监听器
     *
     * @param focusHandleListener
     */
    public void setFocusHandleListener(FocusHandleListener focusHandleListener) {
        this.focusHandleListener = focusHandleListener;
    }

    public void setItemMessageListener(ItemMessageListener itemMessageListener) {
        this.itemMessageListener = itemMessageListener;
    }

    public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener) {
        this.onListItemClickListener = onListItemClickListener;
    }

    public void setOnListItemFocusChangeListener(OnListItemFocusChangeListener onListItemFocusChangeListener) {
        this.onListItemFocusChangeListener = onListItemFocusChangeListener;
    }

    public interface OnListItemClickListener {

        void onListItemClick(ListItem listItem, View clickView);
    }

    public interface OnListItemFocusChangeListener {

        void onListItemFocusChange(boolean hasFocus, View focusView, ListItem listItem);
    }

    public abstract int getItemLayoutId();

    public abstract void handleListItemUI(ContentViewHolder holder, ListItem listItem, int position);

    public abstract void resetListItemUI(ContentViewHolder holder);


    /**
     * 内容适配器
     */
    private class ContentAdapter extends RecyclerView.Adapter<ContentViewHolder> {

        private List<ListItem> dataLists = new ArrayList<>();

        private int itemWidth;
        private int itemHeight;

        public ContentAdapter(List<ListItem> dataLists, int itemWidth, int itemHeight) {
            this.dataLists = dataLists;
            this.itemWidth = itemWidth;
            this.itemHeight = itemHeight;
        }

        @Override
        public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(getItemLayoutId(), parent, false);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = itemWidth;
            layoutParams.height = itemHeight;
            return new ContentViewHolder(view);

        }

        @Override
        public void onBindViewHolder(final ContentViewHolder holder, final int position) {
            ListItem data = dataLists.get(position);
            resetListItemUI(holder);
            handleListItemUI(holder, data, position);
            holder.itemView.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                }
            });

            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        public void resetAllData(List<ListItem> datas) {
            dataLists.clear();
            dataLists.addAll(datas);
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return dataLists.size();
        }
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        private SparseArray<View> sparseArray = new SparseArray<>();
        public View itemView;

        public ContentViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
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


    /**
     * 这个接口用于处理焦点到各个边缘的处理方式,返回true,表示消费掉焦点
     */
    public interface FocusHandleListener {
        /**
         * 当焦点到了顶部边缘
         */
        boolean onTopEdge();

        /**
         * 当焦点到了左部边缘
         */
        boolean onLeftEdge();

        /**
         * 当焦点到了右部边缘
         */
        boolean onRightEdge();

        /**
         * 当焦点到了底部边缘
         */
        boolean onBottomEdge();
    }

    /**
     * 这个接口用于处理页码的处理
     */
    public interface ItemMessageListener {

        /**
         * 列表总的数据数量
         *
         * @param totalNum
         */
        void itemTotalNum(int totalNum);

        /**
         * 列表数据当前index
         *
         * @param index
         */
        void itemCurrentIndex(int index);
    }

    public static class ConfigOption {

        private int columnsCount;
        private int itemWidth;
        private int itemHeight;
        private float linesCount;

        public ConfigOption setSpanCount(int columnsCount) {
            this.columnsCount = columnsCount;
            return this;
        }

        public ConfigOption setItemWidth(int itemWidth) {
            this.itemWidth = itemWidth;
            return this;
        }

        public ConfigOption setItemHeight(int itemHeight) {
            this.itemHeight = itemHeight;
            return this;
        }

        public float getLinesCount() {
            return linesCount;
        }

        public ConfigOption setLinesCount(int linesCount) {
            this.linesCount = linesCount;
            return this;
        }

        public int getColumnsCount() {
            return columnsCount;
        }

        public int getItemWidth() {
            return itemWidth;
        }

        public int getItemHeight() {
            return itemHeight;
        }
    }

    public interface ListLoaderMoreListener {

        /**
         * 分页加载接口
         */
        List<ListItem> loadMore();
    }

    /**
     * 列表View通用的实体类
     */
    public static class ListItem {

    }

}

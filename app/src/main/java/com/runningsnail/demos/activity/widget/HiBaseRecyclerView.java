package com.runningsnail.demos.activity.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

import java.util.List;

/**
 * @author yongjie created on 2019/1/15.
 */
public abstract class HiBaseRecyclerView<E> extends RecyclerView {

    /**
     * RecyclerView数据集
     */
    protected List<E> dataList;

    public HiBaseRecyclerView(Context context) {
        super(context);
    }

    public HiBaseRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HiBaseRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

    /**
     * 请求Item获取焦点，默认聚焦到当前可见的第一个，
     * 如果开启了焦点的记忆功能会聚焦到上一次的位置
     */
    public void requestItemFocus() {

    }

    /**
     * 启用焦点记忆功能,下一次这个控件获得焦点会聚集到
     * 上一次焦点记忆的地方
     */
    public void setFocusMemoryEnable(boolean focusMemory) {

    }

    /**
     * 绑定Item的布局样式
     */
    protected abstract int bindLayoutResId();

    /**
     * 绑定布局Item的样式view,优先级低于{@link #bindLayoutResId()}，
     * 如果{@link #bindLayoutResId()}方法生效了，此方法不会被调用
     */
    protected abstract View bindLayoutView();


    /**
     * 请求指定位置的Item获取焦点
     */
    public void requestItemFocus(int position) {

    }

    /**
     * 焦点到了四个边缘即将脱离这个View时的回调
     * 用于处理焦点的定向处理
     */
    public interface OnFocusInEdgeListener {

    }

    public interface OnHiItemClickListener {

    }

    public interface OnHiItemFocusListener {

    }

    /**
     * 这个接口用于告知内部数据条目信息用于外部去显示数据Index
     */
    public interface OnHiItemPostionListener {
        /**
         * 列表数据总数,这个可能依赖于数据集合
         *
         * @param count
         */
        void onItemTotalCount(int count);

        /**
         * 当前聚焦的Item的Index
         *
         * @param index
         */
        void onItemCurrentFocusItemIndex(int index);
    }

}

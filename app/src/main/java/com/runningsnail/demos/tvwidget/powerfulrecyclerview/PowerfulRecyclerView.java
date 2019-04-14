package com.runningsnail.demos.tvwidget.powerfulrecyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author yongjie created on 2019/1/15.
 */
public class PowerfulRecyclerView extends BaseRecyclerView {

    public PowerfulRecyclerView(Context context) {
        super(context);
    }

    public PowerfulRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PowerfulRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected int bindLayoutResId() {
        return 0;
    }

    @Override
    protected View bindLayoutView() {
        return null;
    }
}
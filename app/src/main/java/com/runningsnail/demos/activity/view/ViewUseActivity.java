package com.runningsnail.demos.activity.view;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class ViewUseActivity extends AppCompatActivity {

    @BindView(R.id.rb)
    RatingBar rb;
    @BindView(R.id.mrb_ratingBar)
    MaterialRatingBar mrbRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_use);
        ButterKnife.bind(this);

        mrbRatingBar.setSupportProgressBackgroundTintList(ColorStateList.valueOf(Color.YELLOW));
        mrbRatingBar.setSupportProgressTintList(ColorStateList.valueOf(Color.BLACK));
    }
}

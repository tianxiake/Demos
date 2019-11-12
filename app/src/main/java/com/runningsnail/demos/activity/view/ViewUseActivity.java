package com.runningsnail.demos.activity.view;

import android.os.Bundle;

import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewUseActivity extends AppCompatActivity {

    @BindView(R.id.rb)
    RatingBar rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_use);
        ButterKnife.bind(this);
    }
}

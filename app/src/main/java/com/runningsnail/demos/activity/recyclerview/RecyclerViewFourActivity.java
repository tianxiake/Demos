package com.runningsnail.demos.activity.recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewFourActivity extends AppCompatActivity {

    @BindView(R.id.rv_content)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_four);
        ButterKnife.bind(this);
    }


}

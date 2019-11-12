package com.runningsnail.demos.activity.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoordinatorLayoutWithFABActivity extends AppCompatActivity {

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout_test_one);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.fab})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.fab:
                showSnackBar();
                break;
            default:
                break;
        }
    }

    private void showSnackBar() {
        Snackbar.make(getWindow().getDecorView(), "我是SnackBar", Snackbar.LENGTH_LONG).show();
    }
}

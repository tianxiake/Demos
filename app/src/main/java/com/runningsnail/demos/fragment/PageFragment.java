package com.runningsnail.demos.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yongjie created on 2018/11/27.
 */
public class PageFragment extends Fragment {

    public static final String TAG = "PageFragment";
    private Context context;

    @BindView(R.id.tv_content)
    TextView textView;

    public PageFragment() {
    }

    public static PageFragment newInstance(Bundle args) {

        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_bottom, null);
        ButterKnife.bind(this, view);

        Bundle arguments = getArguments();
        String content = arguments.getString("content");
        textView.setText(content);
        return view;
    }
}

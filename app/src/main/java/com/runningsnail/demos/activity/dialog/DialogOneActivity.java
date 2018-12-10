package com.runningsnail.demos.activity.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.runningsnail.demos.HiLog;
import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * AlertDialog的各种使用
 */
public class DialogOneActivity extends AppCompatActivity {

    private static final String TAG = "DialogOneActivity";
    @BindView(R.id.btn_one)
    Button dialogOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_one);

        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_one)
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_one:
                Dialog dialogOne = createDialogOne();
                Window window = dialogOne.getWindow();
                window.getDecorView().setPadding(0,0,0,0);
                window.setBackgroundDrawable(new ColorDrawable(Color.RED));
                ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                marginLayoutParams.leftMargin = 0;
                marginLayoutParams.rightMargin = 0;
                window.getDecorView().setLayoutParams(marginLayoutParams);
                WindowManager.LayoutParams attributes = window.getAttributes();
                String s = attributes.toString();
                window.setAttributes(attributes);
                HiLog.d(TAG, "alertDialog:" + s);
                dialogOne.show();
                break;
            default:
                break;
        }
    }

    public Dialog createDialogOne() {
        AlertDialog build = new AlertDialog.Builder(this)
                .setTitle("Hello AlertDialog")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage("你好我是一个对话框")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogOneActivity.this, "确定", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogOneActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                }).setNeutralButton("中间", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogOneActivity.this, "中间", Toast.LENGTH_SHORT).show();
                    }
                }).create();

        return build;
    }
}

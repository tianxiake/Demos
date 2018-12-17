package com.runningsnail.demos.activity.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

    @BindView(R.id.btn_two)
    Button dialogTwo;
    @BindView(R.id.btn_three)
    Button dialogThree;
    @BindView(R.id.btn_four)
    Button dialogFour;
    @BindView(R.id.btn_five)
    Button dialogFive;

    private CharSequence[] charSequences = new CharSequence[]{"红色", "绿色", "蓝色", "白色", "黄色", "红色",
            "绿色", "蓝色", "白色", "黄色", "红色", "绿色", "蓝色", "白色",
            "黄色", "红色", "绿色", "蓝色", "白色", "黄色"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_one);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_one, R.id.btn_two, R.id.btn_three, R.id.btn_four, R.id.btn_five})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_one:
                Dialog dialogOne = createDialogOne();
                dialogOne.show();
                break;
            case R.id.btn_two:
                Dialog dialogTwo = createNormalDialog();
                dialogTwo.show();
                break;
            case R.id.btn_three:
                Dialog dialog = createSingleList();
                dialog.show();
                break;
            case R.id.btn_four:
                Dialog singleChoice = createSingleChoice();
                singleChoice.show();
                break;
            case R.id.btn_five:
                break;
            default:
                break;
        }
    }

    /**
     * Dialog的基本构成部分全部展示
     */
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


    /**
     * 显示一个正常的Dialog
     *
     * @return
     */
    public Dialog createNormalDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage("您中奖了")
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
                }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Toast.makeText(DialogOneActivity.this, "cancle", Toast.LENGTH_SHORT).show();
                    }
                }).create();
        return dialog;
    }


    /**
     * 创建一个简单的列表
     *
     * @return
     */
    public Dialog createSingleList() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("请选择一个颜色")
                .setItems(charSequences, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogOneActivity.this, charSequences[which], Toast.LENGTH_SHORT).show();
                    }
                })
                .create();
        return alertDialog;
    }

    /**
     * 创建单选按钮
     *
     * @return
     */
    public Dialog createSingleChoice() {

        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("选一个呗")
                .setSingleChoiceItems(charSequences, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogOneActivity.this, "第" + which + "个", Toast.LENGTH_SHORT).show();
                    }
                }).create();
        return dialog;
    }


}

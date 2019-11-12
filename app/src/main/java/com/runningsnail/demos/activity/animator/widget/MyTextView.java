package com.runningsnail.demos.activity.animator.widget;

import android.content.Context;

import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.runningsnail.demos.activity.animator.entity.MyCharacter;

/**
 * @author yongjie created on 2019/1/10.
 */
public class MyTextView extends AppCompatTextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setCharText(MyCharacter myCharacter) {
        this.setText(myCharacter.ch + "");
    }

    public MyCharacter getCharText() {
        return new MyCharacter('A');
    }
}

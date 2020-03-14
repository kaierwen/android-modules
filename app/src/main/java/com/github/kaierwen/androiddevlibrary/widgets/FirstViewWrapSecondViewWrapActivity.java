package com.github.kaierwen.androiddevlibrary.widgets;

import android.os.Bundle;

import com.github.kaierwen.androiddevlibrary.base.BaseActivity;

import com.github.kaierwen.androiddevlibrary.R;

/**
 * 线性布局中第一个View（宽度是wrap可变的）占满，第二个View wrap，同时第二个View紧跟这第一个View后面
 *
 * @author kaiyuan.zhang
 * @since 2018/3/19
 */
public class FirstViewWrapSecondViewWrapActivity extends BaseActivity {

    @Override
    public boolean needButterKnife() {
        return true;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_first_view_wrap_second_view_wrap;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}

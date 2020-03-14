package com.github.kaierwen.androiddevlibrary.widgets;

import android.os.Bundle;

import com.github.kaierwen.androiddevlibrary.base.BaseActivity;

import com.github.kaierwen.androiddevlibrary.R;

/**
 * 展示虚线
 *
 * @author kaiyuan.zhang
 * @since 2018/3/14
 */
public class DashedLineViewAcitivty extends BaseActivity {

    @Override
    public boolean needButterKnife() {
        return true;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_dashed_line_view;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}

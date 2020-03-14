package com.github.kaierwen.androiddevlibrary.widgets;

import android.os.Bundle;

import com.github.kaierwen.androiddevlibrary.base.BaseActivity;

import com.github.kaierwen.androiddevlibrary.R;

/**
 * @author zhangky@chinasunfun.com
 * @since 2017/8/16
 */
public class RoundViewActivity extends BaseActivity {

    @Override
    public boolean needButterKnife() {
        return false;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_round_view;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}

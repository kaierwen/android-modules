package com.github.kaierwen.androiddevlibrary.widgets;

import android.os.Bundle;

import github.kaierwen.androiddevlibrary.R;
import com.github.kaierwen.androiddevlibrary.base.BaseActivity;

/**
 * @author kaiyuan.zhang
 * @since 2018/3/15
 */
public class TintActivity extends BaseActivity {

    @Override
    public boolean needButterKnife() {
        return true;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_tint;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}

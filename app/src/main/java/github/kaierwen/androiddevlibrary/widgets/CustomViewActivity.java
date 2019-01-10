package github.kaierwen.androiddevlibrary.widgets;

import android.os.Bundle;

import github.kaierwen.androiddevlibrary.R;
import github.kaierwen.androiddevlibrary.base.BaseActivity;

/**
 * @author zhangky@chinasunfun.com
 * @since 2017/8/4
 */
public class CustomViewActivity extends BaseActivity {

    @Override
    public boolean needButterKnife() {
        return false;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_custom_view;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}

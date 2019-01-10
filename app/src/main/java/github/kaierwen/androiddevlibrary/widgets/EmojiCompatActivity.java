package github.kaierwen.androiddevlibrary.widgets;

import android.os.Bundle;

import github.kaierwen.androiddevlibrary.R;
import github.kaierwen.androiddevlibrary.base.BaseActivity;

/**
 * 表情组件库使用
 *
 * @author kaiyuan.zhang
 * @since 2018/7/10
 */
public class EmojiCompatActivity extends BaseActivity {

    @Override
    public boolean needButterKnife() {
        return true;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_emoji_compat;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}

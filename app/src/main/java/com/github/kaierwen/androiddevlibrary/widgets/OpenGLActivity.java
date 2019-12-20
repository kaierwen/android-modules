package com.github.kaierwen.androiddevlibrary.widgets;

import android.os.Bundle;

import github.kaierwen.androiddevlibrary.R;
import com.github.kaierwen.androiddevlibrary.base.BaseActivity;

/**
 * 测试OpenGL ES
 *
 * @author zhangky@chinasunfun.com
 * @since 2017/8/9
 */
public class OpenGLActivity extends BaseActivity {

    @Override
    public boolean needButterKnife() {
        return false;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_open_gl;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    /**
     * @see <a href="https://developer.android.com/guide/topics/graphics/opengl.html#version-check"/>
     */
    private void checkGlVersion() {

    }
}

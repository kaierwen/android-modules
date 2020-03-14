package com.github.kaierwen.androiddevlibrary.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.jaeger.library.StatusBarUtil;

import butterknife.ButterKnife;
import com.github.kaierwen.androiddevlibrary.R;
import com.github.kaierwen.util.statusbar.OSUtil;
import com.github.kaierwen.util.statusbar.StatusBarUtilRomUI;

/**
 * @author zhangky@chinasunfun.com
 * @since 2017/5/12
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final int INVALID_COLOR = -100;
    protected OSUtil.ROM_TYPE mRomType = OSUtil.ROM_TYPE.OTHER_ROM;

    public abstract boolean needButterKnife();

    /**
     * 布局res文件，需要的参数为R.layout.xxx，和调用setContentView(R.layout.xxx)效果一致
     *
     * @return
     */
    protected abstract int getLayoutRes();

    /**
     * 初始化
     *
     * @param savedInstanceState
     */
    protected abstract void init(Bundle savedInstanceState);

    /**
     * 设置状态栏颜色，需要的参数为R.color.xxx
     *
     * @return colorRes
     */
    protected int getStatusBarColor() {
        return INVALID_COLOR;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        if (needButterKnife()) {
            ButterKnife.bind(this);
            ButterKnife.setDebug(true);
        }
        mRomType = OSUtil.getRomType();
        setStatusBarColor(getStatusBarColor() == INVALID_COLOR ? R.color.colorPrimary : getStatusBarColor());
        init(savedInstanceState);
    }

    /**
     * 设置状态栏颜色
     *
     * @param colorRes
     */
    protected void setStatusBarColor(int colorRes) {
        int color = getResources().getColor(colorRes);
        if (color == Color.WHITE) {//白色
            StatusBarUtil.setColor(this, color, 0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //标题栏亮色模式--黑色字体
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else if (mRomType == OSUtil.ROM_TYPE.MIUI_ROM) {//适配MIUI
                StatusBarUtilRomUI.StatusBarLightMode(this, StatusBarUtilRomUI.TYPE_MIUI);
            } else if (mRomType == OSUtil.ROM_TYPE.FLYME_ROM) {//适配FLYME
                StatusBarUtilRomUI.StatusBarLightMode(this, StatusBarUtilRomUI.TYPE_FLYME);
            } else {//统一设置半透明模板
                StatusBarUtil.setTranslucent(this);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
            StatusBarUtil.setColor(this, color, 0);
            //适配MIUI和FLYME
            if (mRomType == OSUtil.ROM_TYPE.MIUI_ROM) {
                StatusBarUtilRomUI.StatusBarDarkMode(this, StatusBarUtilRomUI.TYPE_MIUI);
            } else if (mRomType == OSUtil.ROM_TYPE.FLYME_ROM) {
                StatusBarUtilRomUI.StatusBarDarkMode(this, StatusBarUtilRomUI.TYPE_FLYME);
            }
        }
    }
}

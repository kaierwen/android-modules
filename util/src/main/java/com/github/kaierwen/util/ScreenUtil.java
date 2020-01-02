package com.github.kaierwen.util;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

/**
 * 屏幕工具
 *
 * @author kaiyuan.zhang
 * @since 2017/12/7
 */
public class ScreenUtil {

    private static final String TAG = ScreenUtil.class.getSimpleName();

    /**
     * 更改屏幕亮度
     *
     * @param activity
     * @param alpha    0-1
     */
    public static void changeWindowAlpha(Activity activity, float alpha) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.alpha = alpha;
        window.setAttributes(params);
    }

    public static int getStatusBarHeight(Resources resource) {
        int height = 0;
        int idStatusBarHeight = resource.getIdentifier(
                "status_bar_height", "dimen", "android");
        if (idStatusBarHeight > 0) {
            height = resource.getDimensionPixelSize(idStatusBarHeight);
        }
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "status bar height = " + height + " px");
        }
        return height;
    }
}

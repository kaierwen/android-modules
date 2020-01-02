package com.github.kaierwen.util;

import android.content.res.Resources;
import android.util.Log;

/**
 * 所有获取高度工具类
 *
 * @author kevinzhang
 * @since 2019-12-31
 */
public class HeightUtil {

    private static final String TAG = HeightUtil.class.getSimpleName();

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

    public static int getActionBarHeight(Resources resource) {
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

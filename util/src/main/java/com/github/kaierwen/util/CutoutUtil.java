package com.github.kaierwen.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;

import java.util.List;

/**
 * 刘海屏适配
 *
 * @author kevinzhang
 * @since 2019-12-31
 */
public class CutoutUtil {

    private static final String TAG = CutoutUtil.class.getSimpleName();

    /**
     * @param activity
     * @see <a href="https://juejin.im/post/5b1930835188257d7541ba33"/>
     */
    @TargetApi(Build.VERSION_CODES.P)
    public static void printCutoutParams(Activity activity) {
        final View decorView = activity.getWindow().getDecorView();

        decorView.post(new Runnable() {
            @Override
            public void run() {
                DisplayCutout displayCutout = decorView.getRootWindowInsets().getDisplayCutout();
                if (displayCutout != null) {
                    Log.e(TAG, "安全区域距离屏幕左边的距离 SafeInsetLeft:" + displayCutout.getSafeInsetLeft());
                    Log.e(TAG, "安全区域距离屏幕右部的距离 SafeInsetRight:" + displayCutout.getSafeInsetRight());
                    Log.e(TAG, "安全区域距离屏幕顶部的距离 SafeInsetTop:" + displayCutout.getSafeInsetTop());
                    Log.e(TAG, "安全区域距离屏幕底部的距离 SafeInsetBottom:" + displayCutout.getSafeInsetBottom());

                    List<Rect> rects = displayCutout.getBoundingRects();
                    if (rects == null || rects.size() == 0) {
                        Log.e(TAG, "不是刘海屏 getBoundingRects is null");
                    } else {
                        Log.e(TAG, "刘海屏数量:" + rects.size());
                        for (Rect rect : rects) {
                            Log.e(TAG, "刘海屏区域：" + rect);
                        }
                    }
                } else {
                    Log.e(TAG, "不是刘海屏 DisplayCutout is null");
                }
            }
        });
    }
}

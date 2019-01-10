package github.kaierwen.util;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

/**
 * 屏幕工具
 *
 * @author kaiyuan.zhang
 * @since 2017/12/7
 */
public class ScreenUtil {

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
}

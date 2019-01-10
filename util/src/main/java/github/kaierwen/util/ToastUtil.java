package github.kaierwen.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;


/**
 *                    .::::.
 *                  .::::::::.
 *                 :::::::::::
 *             ..:::::::::::'
 *           '::::::::::::'
 *             .::::::::::
 *        '::::::::::::::..
 *             ..::::::::::::.
 *           ``::::::::::::::::
 *            ::::``:::::::::'        .:::.
 *           ::::'   ':::::'       .::::::::.
 *         .::::'      ::::     .:::::::'::::.
 *        .:::'       :::::  .:::::::::' ':::::.
 *       .::'        :::::.:::::::::'      ':::::.
 *      .::'         ::::::::::::::'         ``::::.
 *  ...:::           ::::::::::::'              ``::.
 * ```` ':.          ':::::::::'                  ::::..
 *                    '.:::::'                    ':'````..
 *------------------------------------------------------------
 *
 * @author zhangky@chinasunfun.com
 * @since 2017/6/20
 */
public class ToastUtil {

    static Toast toast;
    private static TextView toastText;

    private ToastUtil() {
        throw new AssertionError();
    }

    public static void show(Context context, int resId) {
        if (context == null) return;
        show(context, context.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration) {
        if (context == null) return;
        show(context, context.getResources().getText(resId), duration);
    }

    public static void show(Context context, CharSequence text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, CharSequence text, int duration) {
        show(context, text, duration, Gravity.CENTER);
    }

    public static void show(Context context, CharSequence text, int duration, int gravity) {
        if (toastText == null)
            toast = null;
        if (context == null) return;
        if (toast == null) toast = createCustomToast(context);
        toast.setGravity(gravity, 0, 0);
        toastText.setText(text);
        toast.setDuration(duration);
        toast.show();
    }

    private static Toast createCustomToast(Context context) {
        Toast toast = new Toast(context);
        return toast;
    }

    public static void show(Context context, int resId, Object... args) {
        if (context == null) return;
        show(context, String.format(context.getResources().getString(resId), args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, String format, Object... args) {
        show(context, String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration, Object... args) {
        if (context == null) return;
        show(context, String.format(context.getResources().getString(resId), args), duration);
    }

    public static void show(Context context, String format, int duration, Object... args) {
        show(context, String.format(format, args), duration);
    }
}

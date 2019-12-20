package github.kaierwen.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

/**
 * 封装一些常用的Intent跳转
 *
 * @author kevinzhang
 * @since 2019-12-13
 */
public class IntentUtil {

    private static final String TAG = IntentUtil.class.getSimpleName();
    private static final String HTTPS = "https://";
    private static final String HTTP = "http://";

    /**
     * 通过外部浏览器打开链接
     *
     * @param context
     * @param url     只支持http或https协议
     * @see <a href="https://stackoverflow.com/questions/2201917/how-can-i-open-a-url-in-androids-web-browser-from-my-application/14231438#14231438"/>
     */
    public static void openBrowser(final Context context, String url) {
        if (context == null || TextUtils.isEmpty(url)) return;
        if (!url.startsWith(HTTP) && !url.startsWith(HTTPS)) {
            url = HTTP + url;
        }
        Log.i(TAG, "url = " + url);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}

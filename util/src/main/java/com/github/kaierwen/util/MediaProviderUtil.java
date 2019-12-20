package com.github.kaierwen.util;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.util.Log;

/**
 * @author zhangky@chinasunfun.com
 * @since 2017/4/21
 */
public class MediaProviderUtil {

    private static final String TAG = MediaProviderUtil.class.getSimpleName();

    /**
     * 通知MediaContentProdiver新增文件，后续扫描时添加进去，此方法耗时可能很长，放在子线程中
     *
     * @see <a href="http://www.grokkingandroid.com/adding-files-to-androids-media-library-using-the-mediascanner/"/>
     */
    public static void addPhotoToMediaStore(Context context, String[] filesPath) {
        MediaScannerConnection.scanFile(context.getApplicationContext(),
                filesPath,
                null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String path, Uri uri) {
                        Log.d(TAG, "onScanCompleted path = " + path + " , uri = " + uri);
                    }
                });
    }
}

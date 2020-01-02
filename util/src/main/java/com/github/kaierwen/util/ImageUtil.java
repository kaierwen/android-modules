package com.github.kaierwen.util;

import android.graphics.Bitmap;

/**
 * 图片工具类
 *
 * @author kevinzhang
 * @since 2019-12-30
 */
public class ImageUtil {

    /**
     * 保存{@link Bitmap}到相册
     *
     * <p>
     * Note：由于Android 10安全策略改动，限制了外部SDCard的访问权限，Android 10需要重新适配
     *
     * <p>
     * 1.参见ImageUtils#save()方法<br/>
     * 2.参见{@link android.os.Environment#getExternalStoragePublicDirectory(String)}废弃原因说明
     * 3.参见
     *
     * @param bitmap
     * @return true 保存成功，false 保存失败
     * @see <a href="https://android-developers.googleblog.com/2019/04/android-q-scoped-storage-best-practices.html"/>
     * @see <a href="https://stackoverflow.com/questions/56904485/how-to-save-an-image-in-android-q-using-mediastore"/>
     * @see <a href="https://developer.android.com/about/versions/10/behavior-changes-10"/>
     * @see <a href="https://github.com/Blankj/AndroidUtilCode/blob/master/lib/utilcode/src/main/java/com/blankj/utilcode/util/ImageUtils.java"/>
     */
    public static boolean saveBitmap2Gallery(Bitmap bitmap) {
        if (bitmap == null) return false;
        return true;
    }
}

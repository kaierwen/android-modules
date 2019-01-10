package github.kaierwen.util;

import android.text.TextUtils;

/**
 * 文件操作
 *
 * @author kaiyuan.zhang
 * @since 2018/4/14
 */
public class FileUtil {

    /**
     * 根据后缀名判断是否是图片文件
     *
     * @param file
     * @return
     */
    public static boolean isImageFile(String file) {
        if (!TextUtils.isEmpty(file)) {
            String lowerCase = file.toLowerCase();
            return lowerCase.endsWith(".png") ||
                    lowerCase.endsWith(".jpg") ||
                    lowerCase.endsWith(".jpeg") ||
                    lowerCase.endsWith(".bmp") ||
                    lowerCase.endsWith(".gif");
        }
        return false;
    }

    /**
     * 文件单位
     */
    public static class Unit {

        /**
         * 1Byte
         */
        public static final long BYTE_1 = 1;

        /**
         * 1KB
         */
        public static final long KB_1 = BYTE_1 * 1024;

        /**
         * 1MB
         */
        public static final long MB_1 = KB_1 * 1024;

        /**
         * 1GB
         */
        public static final long GB_1 = MB_1 * 1024;

        /**
         * 1TB
         */
        public static final long TB_1 = GB_1 * 1024;
    }
}

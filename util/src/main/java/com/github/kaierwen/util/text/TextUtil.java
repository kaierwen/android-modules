package com.github.kaierwen.util.text;

import android.text.TextUtils;

/**
 * 文本工具
 *
 * @author kaiyuan.zhang
 * @see <a href="http://blog.csdn.net/bruce_qiwei/article/details/53492554"/>
 * @since 2018/3/7
 */
public class TextUtil {

    /**
     * 判断一个字符是否是中文
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        // 根据字节码判断
        return c >= 0x4E00 && c <= 0x9FA5;
    }

    /**
     * 判断一个字符串是否含有中文
     *
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (isChinese(c)) {
                // 有一个中文字符就返回
                return true;
            }
        }
        return false;
    }
}

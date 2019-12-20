package com.github.kaierwen.util.text;

import android.text.Html;

/**
 * 字体颜色变色工具
 *
 * @author kaiyuan.zhang
 * @since 2016/12/5
 */
public class TextColorUtil {

    /**
     * 使用Html格式化文本颜色
     *
     * @param text
     * @param color
     * @return
     * @see Html#fromHtml(String)
     */
    public static String convert2HtmlTextColor(String text, String color) {
        return "<font color='" + color + "'>" + text + "</font>";
    }
}

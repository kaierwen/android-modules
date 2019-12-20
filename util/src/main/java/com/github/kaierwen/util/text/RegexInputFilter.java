package com.github.kaierwen.util.text;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 支持正则匹配文本的过滤器
 *
 * @author kaiyuan.zhang
 * @since 2018/9/4
 */
public class RegexInputFilter implements InputFilter {

    /**
     * 仅数字
     */
    public static final String REG_NUMBER = "0-9";

    /**
     * 仅字母（大写+小写）
     */
    public static final String REG_LETTER = "a-zA-Z";

    /**
     * 仅小写字母
     */
    public static final String REG_LETTER_LOW = "a-z";

    /**
     * 仅大写字母
     */
    public static final String REG_LETTER_CAP = "A-Z";

    /**
     * 仅汉字
     */
    public static final String REG_CHINESS = "\u4E00-\u9FA5";

    private String mRegex;

    /**
     * 构造方法，支持多个文本限制条件。
     * <p>
     * <p>
     * 如，限制只能输入数字+字母，对象声明：new RegexInputFilter(RegexInputFilter.REG_NUMBER,RegexInputFilter.REG_LETTER)
     *
     * @param regex 代表限制条件的字符串
     */
    public RegexInputFilter(String... regex) {
        if (regex != null && regex.length > 0) {
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            for (int i = 0; i < regex.length; i++) {
                builder.append(regex[i]);
            }
            builder.append("]");
            mRegex = builder.toString();
        }
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String strSource = source.toString();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strSource.length(); i++) {
            char ch = strSource.charAt(i);
            Pattern p = Pattern.compile(mRegex);
            Matcher m = p.matcher(ch + "");
            if (m.matches()) {
                stringBuilder.append(ch);
            }
        }
        return stringBuilder;
    }
}

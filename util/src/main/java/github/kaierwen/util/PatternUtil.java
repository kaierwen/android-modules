package github.kaierwen.util;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 模板
 *
 * @author zhang.kaiyuan
 */
public class PatternUtil {
    private static final String PATTERN_MOBILE_NUM = "^1(3|5|7|8|4)\\d{9}";
    private static final String PATTERN_TEL = "1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}";
    private static final String PATTERN_MONEY = "^([0-9]+|[0-9]{1,3}(,[0-9]{3})*)(.[0-9]{1,2})?$";
    //    private static final String PATTERN_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    private static final String PATTERN_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    private static final String PATTERN_IDCARD = "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})"
            + "(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}"
            + "[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))";
    private static final String PATTERN_CHINESE_ENGLISH = "^[a-zA-Z\u4E00-\u9FA5]+$";
    private static final String PATTERN_NAME_ENGLISH = "^[a-zA-Z0-9\u4E00-\u9FA5]+$";

    public PatternUtil() {
    }

    public static boolean isMobileNum(String num) {
        return TextUtils.isEmpty(num) ? false : num.matches("^1(3|5|7|8|4)\\d{9}");
    }

    public static boolean isPhoneNumber(String num) {
        return TextUtils.isEmpty(num) ? false : num.matches("1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}");
    }

    public static boolean isMoney(String money) {
        return TextUtils.isEmpty(money) ? false : money.matches("^([0-9]+|[0-9]{1,3}(,[0-9]{3})*)(.[0-9]{1,2})?$");
    }

    public static boolean isNumOrLetters(String str) {
        String regEx = "^[A-Za-z0-9_]+$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean isChineseChar(String str) {
        String regEx = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean isIdCard(String idCard) {
        return TextUtils.isEmpty(idCard) ? false : Pattern.matches(PATTERN_IDCARD, idCard.toUpperCase());
    }

    public static boolean isEmail(String email) {
        return TextUtils.isEmpty(email) ? false : Pattern.matches(PATTERN_EMAIL, email);
    }

    /**
     * 输入中文 英文  和数字
     *
     * @param name
     * @return
     */
    public static boolean isName(String name) {
        return TextUtils.isEmpty(name) ? false : Pattern.matches(PATTERN_NAME_ENGLISH, name);
    }

    /**
     * 限制输入中英文
     *
     * @param content
     * @return
     */
    public static boolean isEnglishChinese(String content) {
        return TextUtils.isEmpty(content) ? false : Pattern.matches(PATTERN_CHINESE_ENGLISH, content);
    }
}
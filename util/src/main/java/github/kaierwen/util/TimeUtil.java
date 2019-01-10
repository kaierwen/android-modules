package github.kaierwen.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author kaiyuan.zhang
 * @since 2018/4/4
 */
public class TimeUtil {

    public static final String SERVER_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy.MM.dd";

    public static final String SERVER_M_TIME_FORMAT = "yyyy.MM";

    public static final String SERVER_Y_TIME_FORMAT = "yyyy";

    public static final String TIME_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String TIME_YYYY_M_DD = "yyyy-M-d";

    public static final String TIME_YYYY_MM_DD_HH_MM = "yyyy.MM.dd HH:mm";
    public static final String TIME_YYYY_MM_DD_HH_MM_2 = "yyyy-MM-dd HH:mm";

    public static final String TIME_YYYY_M_D = "yyyy年M月d日";
    public static final String TIME_YYYY_MM = "yyyy年MM月";
    public static final String TIME_MM_DD = "MM月dd日";
    public static final String TIME_MM_DD_HH_MM = "MM-dd HH:mm";
    public static final String TIME_MM_DD_HH_MM_1 = "M月d日 HH:mm";
    public static final String TIME_M_D_HH_MM = "M月d HH:mm";
    public static final String TIME_YY_MM_DD = "yyyy.MM.dd";
    public static final String TIME_HH_MM = "HH:mm";
    public static final String TIME_YYYYMM_DD = "yyyyMM/dd";
    public static final String TIME_HH_MM_SS = "HH:mm:ss";
    public static final String TIME_MM_DD_ = "MM-dd";

    /**
     * 1秒（单位ms）
     */
    public static final long ONE_SEC = 1000;

    /**
     * 1分钟（单位ms）
     */
    public static final long ONE_MIN = ONE_SEC * 60;

    /**
     * 15分钟（单位ms）
     */
    public static final long MIN_15 = ONE_MIN * 15;

    /**
     * 1小时（单位ms）
     */
    public static final long ONE_HOUR = ONE_MIN * 60;

    /**
     * 1天（单位ms）
     */
    public static final long ONE_DAY = ONE_HOUR * 24;

    /**
     * 把long 转换成 日期 再转换成String类型
     */
    public static String transferLongToDate(String dateFormat, Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());
        Date date = new Date(millSec);
        return sdf.format(date);
    }

    /**
     * 时间戳格式化成指定的月日
     *
     * @param time
     * @return 如果是今年，返回M-dd（如5-20）；不是今年，返回yyyy-M-dd（如：2015-8-18）
     */
    public static String formatTime2MonthDay(long time) {
        //今年
        Calendar calendar = Calendar.getInstance();
        int curYear = calendar.get(Calendar.YEAR);
        calendar.setTimeInMillis(time);
        int year = calendar.get(Calendar.YEAR);
        if (curYear == year) {
            //今年
            return transferLongToDate("M-dd", time);
        } else {
            //其他年份
            return transferLongToDate("yyyy-M-dd", time);
        }
    }
}

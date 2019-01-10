package github.kaierwen.util;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author kaiyuan.zhang
 * @since 2018/10/18
 */
public class TimeUtilTest {

    @Test
    public void formatTime2MonthDay() throws Exception {
        long time = 1539762049541L;
        System.out.println(TimeUtil.transferLongToDate(TimeUtil.SERVER_TIME_FORMAT, time));
        System.out.println(TimeUtil.formatTime2MonthDay(time));
    }
}

package github.kaierwen.util;

/**
 * 打印工具类，用于支持常用的打印
 *
 * @author kaiyuan.zhang
 * @since 2018/6/6
 */
public class PrintUtil {

    public static String time() {
        return " , time=" + System.currentTimeMillis();
    }

    public static String threadName() {
        return Thread.currentThread().getName();
    }

    public static String threadNameAndTime() {
        return threadName() + time();
    }
}

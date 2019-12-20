package com.github.kaierwen.util;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 列表工具类
 *
 * @author kaiyuan.zhang
 * @since 2017/12/27
 */
public class ListUtil {

    /**
     * List去掉重复数据
     *
     * @param list
     * @return
     * @see <a href="http://blog.csdn.net/myhui123/article/details/38983299"/>
     */
    public static List<String> removeDuplicate(List<String> list) {
        Set set = new LinkedHashSet<String>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }
}

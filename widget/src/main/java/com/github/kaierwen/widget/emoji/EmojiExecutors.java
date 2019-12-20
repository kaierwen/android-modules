package com.github.kaierwen.widget.emoji;

import androidx.annotation.NonNull;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 封装用于执行表情数据库操作的线程池
 *
 * @author kaiyuan.zhang
 * @since 2018/7/9
 */
public class EmojiExecutors {

    /**
     * 线程池参数
     */
    private int corePoolSize = 5;
    private int maximumPoolSize = 10;
    /**
     * 设置为500ms
     */
    private long keepAliveTime = 500;
    private TimeUnit unit = TimeUnit.MILLISECONDS;

    private static EmojiExecutors sInstance;
    private static ThreadPoolExecutor sThreadPoolExecutor;

    private EmojiExecutors() {
        if (sThreadPoolExecutor == null) {
            sThreadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                    keepAliveTime, unit,
                    new LinkedBlockingQueue<Runnable>(30),
                    new EmojiThreadFactory());
        }
    }

    public static EmojiExecutors getInstance() {
        if (sInstance == null) {
            sInstance = new EmojiExecutors();
        }
        return sInstance;
    }

    public void execute(Runnable runnable) {
        if (sThreadPoolExecutor != null) {
            sThreadPoolExecutor.execute(runnable);
        }
    }

    /**
     * 自定义{@link ThreadFactory}，实现线程池重命名，以便后续排查问题，实现参考Executor.DefaultThreadFactory
     */
    private class EmojiThreadFactory implements ThreadFactory {

        private AtomicInteger poolNumber = new AtomicInteger(1);
        private ThreadGroup group;
        private AtomicInteger threadNumber = new AtomicInteger(1);
        private String namePrefix;

        EmojiThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "emoji-pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(@NonNull Runnable r) {
            Thread thread = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            return thread;
        }
    }
}

package com.github.kaierwen.widget.emoji.db;

import android.content.Context;

import java.util.List;

import com.github.kaierwen.widget.emoji.db.entity.EmojiEntity;

/**
 * 数据仓库
 *
 * @author kaiyuan.zhang
 * @since 2018/7/6
 */
public class EmojiDataRepository {

    private static EmojiDataRepository sInstance;
    private static Context sContext;

    private BaseEmojiDatabase database;

    private EmojiDataRepository(Context context) {
        sContext = context;
    }

    public static EmojiDataRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new EmojiDataRepository(context);
        }
        return sInstance;
    }

    public BaseEmojiDatabase getDatabase() {
        return BaseEmojiDatabase.getInstance(sContext);
    }

    public List<EmojiEntity> getAllEmojies() {
        return getDatabase().emojiDao().getAllEmojies();
    }
}

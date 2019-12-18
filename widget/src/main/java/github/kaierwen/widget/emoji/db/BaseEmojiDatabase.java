package github.kaierwen.widget.emoji.db;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.orhanobut.logger.Logger;

import java.util.List;

import github.kaierwen.util.PrintUtil;
import github.kaierwen.widget.emoji.EmojiExecutors;
import github.kaierwen.widget.emoji.db.dao.EmojiDao;
import github.kaierwen.widget.emoji.db.entity.EmojiEntity;

/**
 * @author kaiyuan.zhang
 * @since 2018/6/29
 */
@Database(entities = {EmojiEntity.class}, version = 1)
public abstract class BaseEmojiDatabase extends RoomDatabase {

    public static final String EMOJI_DATABASE_NAME = "emoji-db";
    private static BaseEmojiDatabase sInstance;
    private static EmojiExecutors emojiExecutors;

    public abstract EmojiDao emojiDao();

    public static BaseEmojiDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = buildDatabase(context, EmojiExecutors.getInstance());
        }
        return sInstance;
    }

    private static BaseEmojiDatabase buildDatabase(final Context context, final EmojiExecutors executors) {
        return Room.databaseBuilder(context, BaseEmojiDatabase.class, EMOJI_DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Logger.d(PrintUtil.threadNameAndTime());
                        executors.execute(new Runnable() {
                            @Override
                            public void run() {
                                Logger.d(PrintUtil.threadNameAndTime());
                                BaseEmojiDatabase database = getInstance(context);
                                List<EmojiEntity> emojies = EmojiGenerator.generateEmojies();
                                insertData(database, emojies);
                            }
                        });
                    }
                }).build();
    }

    private static void insertData(final BaseEmojiDatabase database, final List<EmojiEntity> emojiEntities) {
        database.runInTransaction(new Runnable() {
            @Override
            public void run() {
                Logger.d(PrintUtil.threadNameAndTime());
                database.emojiDao().insertAll(emojiEntities);
            }
        });
    }
}

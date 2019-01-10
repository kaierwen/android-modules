package github.kaierwen.widget.emoji.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import github.kaierwen.widget.emoji.db.entity.EmojiEntity;

/**
 * @author kaiyuan.zhang
 * @since 2018/6/29
 */
@Dao
public interface EmojiDao {

    @Query("SELECT * FROM face_positive")
    List<EmojiEntity> getAllEmojies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<EmojiEntity> entities);

    @Query("SELECT * FROM face_positive WHERE code = :code")
    EmojiEntity getEmoji(String code);
}

package github.kaierwen.widget.emoji.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * @author kaiyuan.zhang
 * @since 2018/6/29
 */
@Entity(tableName = "face_positive")
public class EmojiEntity {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "code")
    public String code;

    @ColumnInfo(name = "cldrShortName")
    public String cldrShortName;

}

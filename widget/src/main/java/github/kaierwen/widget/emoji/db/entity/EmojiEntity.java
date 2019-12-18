package github.kaierwen.widget.emoji.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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

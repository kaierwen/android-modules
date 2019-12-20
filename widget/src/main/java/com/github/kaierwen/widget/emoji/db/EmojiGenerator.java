package com.github.kaierwen.widget.emoji.db;

import java.util.ArrayList;
import java.util.List;

import com.github.kaierwen.widget.emoji.db.entity.EmojiEntity;

/**
 * 表情的Unicode编码生成类
 * <p>
 * <p>
 * 所有Unicode表情参阅<a href="http://www.unicode.org/emoji/charts/full-emoji-list.html">full-emoji-list</a>
 * </p>
 *
 * @author kaiyuan.zhang
 * @see <a href="https://stackoverflow.com/questions/26893796/how-set-emoji-by-unicode-in-a-textview">how-set-emoji-by-unicode-in-a-textview</a>
 * @see <a href="https://https://apps.timwhitlock.info/emoji/tables/unicode">unicode</a>
 * @since 2018/6/27
 */
public class EmojiGenerator {

    /**
     * Code
     */
    private static String[] FACE_POSITIVE_CODE = {
            "0x1f600", "0x1f601",
            "0x1f602", "0x1f923",
            "0x1f603", "0x1f604",
            "0x1f605", "0x1f606"
//            0x1f609, 0x1f60a, 0x1f60b, 0x1f60e,
//            0x1f60d, 0x1f618, 0x1f970, 0x1f617,
//            0x1f619, 0x1f61a, 0x263a, 0x1f642,
//            0x1f917, 0x1f929
    };

    /**
     * CLDR Short Name
     */
    private static String[] FACE_POSITIVE_CLDR_SHORT_NAME = {
            "grinning face", "beaming face with smiling eyes",
            "face with tears of joy", "rolling on the floor laughing",
            "grinning face with big eyes", "grinning face with smiling eyes",
            "grinning face with sweat", "grinning squinting face"
    };

    public static List<EmojiEntity> generateEmojies() {
        List<EmojiEntity> emojies = new ArrayList<>();
        for (int i = 0; i < FACE_POSITIVE_CODE.length; i++) {
            EmojiEntity emojiEntity = new EmojiEntity();
            emojiEntity.code = FACE_POSITIVE_CODE[i];
            emojiEntity.cldrShortName = FACE_POSITIVE_CLDR_SHORT_NAME[i];
            emojies.add(emojiEntity);
        }
        return emojies;
    }
}

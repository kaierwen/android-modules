package github.kaierwen.widget.emoji;

import android.content.Context;
import android.support.text.emoji.EmojiCompat;
import android.support.text.emoji.bundled.BundledEmojiCompatConfig;

import com.orhanobut.logger.Logger;

import java.util.List;

import github.kaierwen.widget.emoji.db.BaseEmojiDatabase;
import github.kaierwen.widget.emoji.db.EmojiDataRepository;
import github.kaierwen.widget.emoji.db.entity.EmojiEntity;

/**
 * {@link EmojiCompat}的初始化
 *
 * @author kaiyuan.zhang
 * @see <a href="https://developer.android.com/guide/topics/ui/look-and-feel/emoji-compat"/>
 * <br/>
 * <a href="https://github.com/googlesamples/android-EmojiCompat"/>
 * @since 2018/6/26
 */
public class EmojiManager {

    /**
     * Change this to {@code false} when you want to use the downloadable Emoji font.
     */
    private static final boolean USE_BUNDLED_EMOJI = true;

    private static EmojiManager sInstance;
    private static Context sContext;

    private EmojiManager(Context context) {
        sContext = context;
        init();
    }

    public static EmojiManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new EmojiManager(context);
        }
        return sInstance;
    }

    private void init() {
        EmojiCompat.Config config = null;
        if (USE_BUNDLED_EMOJI) {
            // Use the bundled font for EmojiCompat
            config = new BundledEmojiCompatConfig(sContext);
        }
        EmojiCompat.init(config);
    }

    public List<EmojiEntity> getAllEmojies() {
        return EmojiDataRepository.getInstance(sContext).getAllEmojies();
    }
}

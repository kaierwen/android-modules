package github.kaierwen.widget.emoji;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.emoji.widget.EmojiTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import java.util.List;

import github.kaierwen.util.PrintUtil;
import github.kaierwen.widget.R;
import github.kaierwen.widget.adapter.QuickRecyclerAdapter;
import github.kaierwen.widget.emoji.db.entity.EmojiEntity;

/**
 * 显示Emoji的控件，采用{@link RecyclerView}实现
 *
 * @author kaiyuan.zhang
 * @since 2018/6/27
 */
public class EmojiFragment extends Fragment {

    /**
     * 方向参数值：{@link #ORIENTATION_VERTICAL}，{@link #ORIENTATION_HORIZONTAL}的关键字
     */
    public static final String EXTRA_ORIENTATION = "extra_orientation";

    /**
     * {@link GridLayoutManager#GridLayoutManager(Context, int)}中的第二个参数
     */
    public static final String EXTRA_SPAN_COUNT = "extra_span_count";

    public static final int ORIENTATION_VERTICAL = 1;
    public static final int ORIENTATION_HORIZONTAL = 2;
    public static final int DEFAULT_SPAN_COUNT = 5;

    private RecyclerView mRecyclerView;
    private QuickRecyclerAdapter<EmojiEntity> mAdapter;
    private int mOrientation = ORIENTATION_HORIZONTAL;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        int spanCount = DEFAULT_SPAN_COUNT;
        if (arguments != null) {
            mOrientation = arguments.getInt(EXTRA_ORIENTATION, ORIENTATION_HORIZONTAL);
            spanCount = arguments.getInt(EXTRA_SPAN_COUNT, DEFAULT_SPAN_COUNT);
        }
        if (mRecyclerView == null) {
            mRecyclerView = new RecyclerView(getContext());
            mRecyclerView.setLayoutParams(new RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount) {
                @Override
                public boolean canScrollHorizontally() {
                    return mOrientation == ORIENTATION_HORIZONTAL;
                }

                @Override
                public boolean canScrollVertically() {
                    return mOrientation == ORIENTATION_VERTICAL;
                }
            };
            mRecyclerView.setLayoutManager(layoutManager);
        }
        if (mAdapter == null) {
            mAdapter = new QuickRecyclerAdapter<EmojiEntity>(getContext(), R.layout.item_emoji_text) {
                @Override
                protected void convert(RecyclerView.ViewHolder itemView, EmojiEntity entity) {
                    EmojiTextView emojiTextView = (EmojiTextView) itemView.itemView;
                    emojiTextView.setText(entity.code);
                }
            };
        }
        mRecyclerView.setAdapter(mAdapter);
        return mRecyclerView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EmojiExecutors.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                //在子线程中读取所有数据库
                Logger.d(PrintUtil.threadNameAndTime());
                final List<EmojiEntity> allEmojies = EmojiManager.getInstance(getContext()).getAllEmojies();
                for (int i = 0; i < allEmojies.size(); i++) {
                    EmojiEntity emojiEntity = allEmojies.get(i);
                    Logger.d("id=" + emojiEntity.id +
                            "\ncode=" + emojiEntity.code +
                            "\nname=" + emojiEntity.cldrShortName);
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Logger.d(PrintUtil.threadNameAndTime());
                        setEmojiData(allEmojies);
                    }
                });
            }
        });
    }

    /**
     * 设置表情数据
     *
     * @param emojis
     */
    public void setEmojiData(List<EmojiEntity> emojis) {
        if (emojis != null && emojis.size() > 0) {
            mAdapter.addAll(emojis);
        }
    }
}

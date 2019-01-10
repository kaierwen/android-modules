package github.kaierwen.widget.emoji.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.androidkun.xtablayout.XTabLayout;

import java.util.ArrayList;

import github.kaierwen.widget.R;

/**
 * 封装表情控件，此控件包含TabLayout+ViewPager
 * <p>
 * <p>
 * 提供两种UI模式：<br />
 * 1.TabLayout在上，ViewPager在下
 * 2.TabLayout在下，ViewPager在上
 *
 * @author kaiyuan.zhang
 * @since 2018/6/27
 */
public class EmojiLayout extends LinearLayout {

    private XTabLayout mTabLayout;
    private ViewPager mViewPager;

    public EmojiLayout(Context context) {
        this(context, null);
    }

    public EmojiLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmojiLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        inflate(getContext(), R.layout.layout_emoji_tab_top, this);
        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewPager);

        mTabLayout.setupWithViewPager(mViewPager);
    }

    private class EmojiPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> mFragments = new ArrayList<>();

        public EmojiPagerAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
        }

        public EmojiPagerAdapter(FragmentManager fm, ArrayList<Fragment> frags) {
            super(fm);
            mFragments.clear();
            mFragments.addAll(frags);
        }

        @Override
        public Fragment getItem(int i) {
            return mFragments.get(i);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}

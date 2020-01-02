package com.github.kaierwen.androiddevlibrary;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSON;
import com.github.kaierwen.androiddevlibrary.base.BaseActivity;
import com.github.kaierwen.androiddevlibrary.data.DTO;
import com.github.kaierwen.androiddevlibrary.frag.BaseListFragment;
import com.github.kaierwen.util.CutoutUtil;
import com.github.kaierwen.util.ScreenUtil;
import com.github.kaierwen.util.StreamUtil;
import com.github.kaierwen.widget.emoji.EmojiManager;
import com.google.android.material.tabs.TabLayout;
import com.orhanobut.logger.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import github.kaierwen.androiddevlibrary.BuildConfig;
import github.kaierwen.androiddevlibrary.R;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    @BindView(R.id.view_pager)
    ViewPager view_pager;

    private DTO mDTO;
    private MyAdapter mAdapter;

    @Override
    public boolean needButterKnife() {
        return true;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.white;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mAdapter = new MyAdapter(getSupportFragmentManager());
        view_pager.setAdapter(mAdapter);
        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab_layout.setupWithViewPager(view_pager);

        String data = "";
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.data);
            data = StreamUtil.inputStreamToString(inputStream);
            Logger.d("data = " + data);
        } catch (Resources.NotFoundException e) {
            if (BuildConfig.DEBUG) e.printStackTrace();
        }

        try {
            mDTO = JSON.parseObject(data, DTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mDTO != null) {
            mAdapter.setDatas(mDTO.getData());
        }
//        startActivity(new Intent(this, AndroidResizeImageActivity.class));
        EmojiManager.getInstance(this);

        ScreenUtil.getStatusBarHeight(getResources());
        CutoutUtil.printCutoutParams(this);


//        WindowManager.LayoutParams lp = getWindow().getAttributes();
//
//        //下面图1
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
////            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER;
////        }
//        //下面图2
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
////            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
////        }
//        //下面图3
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT;
//        }
//        getWindow().setAttributes(lp);

    }

    private void parseJsonData() {
        InputStream in = getResources().openRawResource(R.raw.data);
        try {
            JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private class MyAdapter extends FragmentStatePagerAdapter {

        private List<DTO.DataBean> mDatas = new ArrayList<>();

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        public List<DTO.DataBean> getDatas() {
            return mDatas;
        }

        public void setDatas(List<DTO.DataBean> datas) {
            mDatas.clear();
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mDatas.get(position).getTitle();
        }

        @Override
        public Fragment getItem(int position) {
            return BaseListFragment.newInstance(mDatas.get(position).getTitle(), mDatas.get(position).getList());
        }

        //返回POSITION_NONE，能实现Fragment删除
        //see http://stackoverflow.com/questions/10396321/remove-fragment-page-from-viewpager-in-android
//        @Override
//        public int getItemPosition(Object object) {
//            return POSITION_NONE;
//        }

        @Override
        public int getCount() {
            return mDatas.size();
        }
    }
}


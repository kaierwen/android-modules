package com.github.kaierwen.androiddevlibrary.frag;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.ListFragment;

import com.github.kaierwen.androiddevlibrary.data.DTO;
import com.github.kaierwen.androiddevlibrary.download.DownloadDemo;
import com.github.kaierwen.androiddevlibrary.openlibrary.KotlinActivity;
import com.github.kaierwen.androiddevlibrary.openlibrary.X5WebViewActivity;
import com.github.kaierwen.androiddevlibrary.widgets.CustomViewActivity;
import com.github.kaierwen.androiddevlibrary.widgets.DashedLineViewAcitivty;
import com.github.kaierwen.androiddevlibrary.widgets.EmojiCompatActivity;
import com.github.kaierwen.androiddevlibrary.widgets.FirstViewWrapSecondViewWrapActivity;
import com.github.kaierwen.androiddevlibrary.widgets.OpenGLActivity;
import com.github.kaierwen.androiddevlibrary.widgets.RoundViewActivity;
import com.github.kaierwen.androiddevlibrary.widgets.TintActivity;

import java.util.ArrayList;
import java.util.List;

import github.kaierwen.androiddevlibrary.R;

/**
 * @author zhangky@chinasunfun.com
 * @since 2017/5/17
 */
public class BaseListFragment extends ListFragment {
    private static final String EXTRA_TITLE = "extra_title";
    protected String mTitle;
    private static ArrayList<DTO.DataBean.ListBean> mListBeans = new ArrayList<>();
    private MyAdapter mAdapter;

    public static BaseListFragment newInstance(String title, List<DTO.DataBean.ListBean> listBean) {
        // Supply num input as an argument.
        BaseListFragment fragment = new BaseListFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_TITLE, title);
        fragment.setArguments(args);
        mListBeans.clear();
        mListBeans.addAll(listBean);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle = getArguments() != null ? getArguments().getString(EXTRA_TITLE) : null;
    }

    /**
     * The Fragment's UI is just a simple text view showing its
     * instance number.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pager_list, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new MyAdapter();
        setListAdapter(mAdapter);
        mAdapter.setDatas(mListBeans);
//        setListAdapter(new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1, Cheeses.sCheeseStrings));
    }

    private ArrayList<String> getListStrings() {
        ArrayList<String> list = new ArrayList<>();
        return list;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.i("FragmentList", "Item clicked: " + id);
        String text = ((DTO.DataBean.ListBean) mAdapter.getItem(position)).getText();
        if ("kotlin".equals(text)) {
            startActivity(new Intent(getActivity(), KotlinActivity.class));
        } else if ("CustomView".equals(text)) {
            startActivity(new Intent(getActivity(), CustomViewActivity.class));
        } else if ("OpenGL ES".equals(text)) {
            startActivity(new Intent(getActivity(), OpenGLActivity.class));
        } else if ("RoundView".equals(text)) {
            startActivity(new Intent(getActivity(), RoundViewActivity.class));
        } else if ("X5WebView".equals(text)) {
            startActivity(new Intent(getActivity(), X5WebViewActivity.class));
        } else if ("DashedLineView".equals(text)) {
            startActivity(new Intent(getActivity(), DashedLineViewAcitivty.class));
        } else if ("Tint".equals(text)) {
            startActivity(new Intent(getActivity(), TintActivity.class));
        } else if ("First TextView weight = 1, Second TextView wrap_content".equals(text)) {
            startActivity(new Intent(getActivity(), FirstViewWrapSecondViewWrapActivity.class));
        } else if ("EmojiCompat".equals(text)) {
            startActivity(new Intent(getActivity(), EmojiCompatActivity.class));
        } else if ("DownloadManager Test".equals(text)) {
            startActivity(new Intent(getActivity(), DownloadDemo.class));
        }
    }

    private class MyAdapter extends BaseAdapter {

        private ArrayList<DTO.DataBean.ListBean> mDatas = new ArrayList<>();

        public ArrayList<DTO.DataBean.ListBean> getDatas() {
            return mDatas;
        }

        public void setDatas(ArrayList<DTO.DataBean.ListBean> datas) {
            mDatas.clear();
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder;
            if (convertView == null) {
                convertView = View.inflate(getActivity(), android.R.layout.simple_list_item_2, null);
                holder = new Holder();
                holder.textView1 = (TextView) convertView.findViewById(android.R.id.text1);
                holder.textView2 = (TextView) convertView.findViewById(android.R.id.text2);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            holder.textView1.setText(mDatas.get(position).getText());
            holder.textView2.setText(mDatas.get(position).getUrl());
            return convertView;
        }

        class Holder {
            TextView textView1;
            TextView textView2;
        }
    }
}

package com.github.kaierwen.widget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/24.
 */
public abstract class QuickRecyclerAdapter<T> extends RecyclerView.Adapter {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private View mHeaderView;
    int resId;
    Context context;
    protected List<T> dataList;
    ChoiceMode mode = ChoiceMode.CHOICE_MODE_NONE;
    protected OnRecyclerViewItemClickListener mOnItemClickListener = null;

    enum ChoiceMode {
        CHOICE_MODE_NONE, CHOICE_MODE_SINGLE, CHOICE_MODE_MULTIPLE;

        ChoiceMode() {
        }
    }

    public QuickRecyclerAdapter(int resId, Context context, List<T> dataList) {
        this.resId = resId;
        this.context = context;
        this.dataList = dataList == null ? new ArrayList<T>() : new ArrayList<T>(dataList);
    }

    public QuickRecyclerAdapter(int resId, Context context) {
        this(resId, context, null);
    }

    public QuickRecyclerAdapter(Context context, int resId) {
        this(resId, context, null);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER)
            return new RecyclerView.ViewHolder(mHeaderView) {
                @Override
                public String toString() {
                    return super.toString();
                }
            };
        if (resId == 0)
            throw new IllegalArgumentException("Can't instance Adapter because of resId is con provide");
        return new RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(resId, parent, false)) {
            @Override
            public String toString() {
                return super.toString();
            }
        };
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;
        final int pos = getRealPosition(holder);
        convert(holder, dataList.get(pos));
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    protected abstract void convert(RecyclerView.ViewHolder itemView, T item);

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return mHeaderView == null ? dataList.size() : dataList.size() + 1;
    }

    public List<T> getData() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public void add(int posi, T elem) {
        dataList.add(posi, elem);
        notifyDataSetChanged();
    }

    public void add(T elem) {
        dataList.add(elem);
        notifyDataSetChanged();
    }

    public void set(T oldElem, T newElem) {
        set(dataList.indexOf(oldElem), newElem);
    }

    public void set(int index, T elem) {
        dataList.set(index, elem);
        notifyDataSetChanged();
    }


    public void addAll(List<T> elem) {
        if (elem!=null&&elem.size()!=0) {
            dataList.addAll(elem);
            notifyDataSetChanged();
        }
    }

    /**
     * 预留初始化集合
     */
    public  void setInitList(){};

    public List<T> getList(){
        return dataList;
    }

    public void remove(T elem) {
        dataList.remove(elem);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        dataList.remove(index);
        notifyDataSetChanged();
    }

    public boolean contains(T elem) {
        return dataList.contains(elem);
    }

    public int indexOf(T elem) {
        return dataList.indexOf(elem);
    }

    /**
     * Clear data list
     */
    public void clear() {
        dataList.clear();
        notifyDataSetChanged();
    }


    public void replaceAll(List<T> elem) {
        dataList.clear();
        if (elem!=null&&elem.size()!=0)
            dataList = elem;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
}

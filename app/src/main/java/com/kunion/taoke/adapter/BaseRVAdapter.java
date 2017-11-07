package com.kunion.taoke.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kunion.taoke.util.ListUtil;

import java.util.List;


/**
 * Created by Administrator on 2017/10/30.
 */

public abstract class BaseRVAdapter<T> extends RecyclerView.Adapter<ItemViewHolder> {

    protected List<T> mDatas;
    protected int mLayoutId;
    protected int mVariableId;

    public BaseRVAdapter(int layoutId, int variableId, List<T> datas) {

        mDatas = datas;
        mVariableId = variableId;
        mLayoutId = layoutId;

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        convert(holder, mDatas.get(position));
    }

    public abstract void convert(ItemViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void refreshDatas(List<T> infos) {
        if(ListUtil.isNotBlank(infos)) {
            mDatas.clear();
            mDatas.addAll(infos);
            notifyDataSetChanged();
        }
    }

    public void loadMoreDatas(List<T> infos) {
        if(ListUtil.isNotBlank(infos)) {
            mDatas.addAll(infos);
            notifyDataSetChanged();
        }
    }
}

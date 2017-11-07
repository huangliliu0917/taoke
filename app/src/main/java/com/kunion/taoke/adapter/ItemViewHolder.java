package com.kunion.taoke.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by Administrator on 2017/10/30.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {


    private ViewDataBinding mBinding;

    public ItemViewHolder(View itemView) {
        super(itemView);
        mBinding = DataBindingUtil.bind(itemView);
    }

    public ItemViewHolder setBinding(int variableId, Object object) {
        mBinding.setVariable(variableId, object);
        mBinding.executePendingBindings();
        return this;
    }

}

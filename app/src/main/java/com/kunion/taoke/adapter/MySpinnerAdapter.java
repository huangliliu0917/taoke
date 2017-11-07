package com.kunion.taoke.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kunion.taoke.R;

import java.util.List;

/**
 * Created by Administrator on 2017/11/1.
 */
public class MySpinnerAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private List<String> mBeans;
    private Context mContext;

    public MySpinnerAdapter(Context pContext, List<String> pList) {
        this.mContext = pContext;
        this.mBeans = pList;
        this.mLayoutInflater = (LayoutInflater)pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return mBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null || null == convertView.getTag()) {
            convertView=mLayoutInflater.inflate(R.layout.layout_spinner, null);
            viewHolder = new ViewHolder();
            viewHolder.mTextView = convertView.findViewById(R.id.spinner_textview);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String string = mBeans.get(position);
        viewHolder.mTextView.setText(string);
        return convertView;
    }

    class ViewHolder {
        public TextView mTextView;

    }
}

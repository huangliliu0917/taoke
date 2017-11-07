package com.kunion.taoke.sale;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.kunion.taoke.BR;
import com.kunion.taoke.R;
import com.kunion.taoke.UserInfoMSG;
import com.kunion.taoke.adapter.BaseRVAdapter;
import com.kunion.taoke.adapter.ItemViewHolder;
import com.kunion.taoke.adapter.MySpinnerAdapter;
import com.kunion.taoke.model.remote.rest.info.SaleInfo;
import com.kunion.taoke.util.FormatUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/10/10.
 */

public class SaleFragment extends Fragment implements SaleContract.View {

    private SaleContract.Presenter mPresenter;

    private RecyclerView mRecyclerView;

    private List<String> mGroups = new ArrayList<>();
    private List<String> mInitGroups = new ArrayList<>();

    private RefreshLayout mRefreshLayout;
    private BaseRVAdapter<SaleInfo> mAdapter;

    private AppCompatSpinner mSpinner;
    private TextView mSaleCount;

    private UserInfoMSG mUIMSG = UserInfoMSG.getInstance();

    @Override
    public void setPresenter(@NonNull SaleContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public static SaleFragment newInstance() {
        return new SaleFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab_common);
        fab.setImageResource(R.drawable.ic_top);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.sale_frag, container, false);
        mRecyclerView = root.findViewById(R.id.recyclerview);
        mRefreshLayout = root.findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.onLoadMore();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.onRefresh();
            }
        });

        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final int variableId = BR.info;
        mAdapter = new BaseRVAdapter<SaleInfo>(R.layout.sale_adapter, variableId, new ArrayList<SaleInfo>()) {
            @Override
            public void convert(ItemViewHolder holder, SaleInfo saleInfo) {
                holder.setBinding(variableId, saleInfo);
            }
        };

        //设置adapter
        mRecyclerView.setAdapter(mAdapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.HORIZONTAL));

        mSpinner = root.findViewById(R.id.spinner);
        mSaleCount = root.findViewById(R.id.sales_count);

        List<String> mItems = mUIMSG.getGroupName();
        mItems.add("全部");

        mSpinner.setAdapter(new MySpinnerAdapter(getContext(), mItems));
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                changeGroups(position);
                mPresenter.onRefresh();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mInitGroups = mUIMSG.getGroupName();
        return root;
    }


    private void changeGroups(int position) {
        mGroups.clear();
        if(position == mInitGroups.size()) {
            mGroups.addAll(mInitGroups);
        } else {
            mGroups.add(mInitGroups.get(position));
        }
        mPresenter.setSelectedGroups(mGroups);
    }

    @Override
    public void onRefresh(List<SaleInfo> infos) {
        mAdapter.refreshDatas(infos);
        mRefreshLayout.finishRefresh();
    }

    @Override
    public void onLoadMore(List<SaleInfo> infos) {
        mAdapter.loadMoreDatas(infos);
        mRefreshLayout.finishLoadmore(500);
    }

    @Override
    public void showCount(int count) {
        mSaleCount.setText(FormatUtil.getTextNumber(R.string.select_sale_count, count));
    }
}

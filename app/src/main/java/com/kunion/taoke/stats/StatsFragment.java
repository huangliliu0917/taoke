package com.kunion.taoke.stats;

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
import com.kunion.taoke.model.remote.rest.info.StatsInfo;
import com.kunion.taoke.util.FormatUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 */

public class StatsFragment extends Fragment implements StatsContract.View {

    private StatsContract.Presenter mPresenter;

    private RecyclerView mRecyclerView;

    private List<String> mInitGroups = new ArrayList<>();

    private RefreshLayout mRefreshLayout;
    private BaseRVAdapter<StatsInfo> mAdapter;

    private UserInfoMSG mUIMSG = UserInfoMSG.getInstance();

    @Override
    public void setPresenter(@NonNull StatsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public static StatsFragment newInstance() {
        return new StatsFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab_common);
        fab.setImageResource(R.drawable.ic_top);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mRecyclerView != null) {
                    mRecyclerView.smoothScrollToPosition(0);
                }
            }
        });
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

        final int variableId = BR.statsInfo;
        mAdapter = new BaseRVAdapter<StatsInfo>(R.layout.stats_adapter, variableId, new ArrayList<StatsInfo>()) {
            @Override
            public void convert(ItemViewHolder holder, StatsInfo statsInfo) {
                holder.setBinding(variableId, statsInfo);
            }
        };

        //设置adapter
        mRecyclerView.setAdapter(mAdapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.HORIZONTAL));

        AppCompatSpinner mSpinner = root.findViewById(R.id.spinner);
        root.findViewById(R.id.sales_count).setVisibility(View.GONE);

        List<String> mItems = mUIMSG.getGroupName();

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
        mPresenter.setSelectedGroups(mInitGroups.get(position));
    }

    @Override
    public void onRefresh(List<StatsInfo> infos) {
        mAdapter.refreshDatas(infos);
        mRefreshLayout.finishRefresh();
    }

    @Override
    public void onLoadMore(List<StatsInfo> infos) {
        mAdapter.loadMoreDatas(infos);
        mRefreshLayout.finishLoadmore(500);
    }

}

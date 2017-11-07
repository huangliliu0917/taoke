package com.kunion.taoke.stats;

import android.support.annotation.NonNull;

import com.kunion.taoke.model.TasksSource;
import com.kunion.taoke.model.remote.rest.BaseBean;
import com.kunion.taoke.model.remote.rest.info.StatsInfo;
import com.kunion.taoke.model.remote.rest.resp.StatsResp;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/10/10.
 */

public class StatsPresenter implements StatsContract.Presenter {

    @NonNull
    private TasksSource mTasksRepository;

    @NonNull
    private final StatsContract.View mStatsView;

    private int mPage = 0;
    private int mSize = 10;

    private String mSelectGroup;

    public StatsPresenter(@NonNull TasksSource tasksSource, @NonNull StatsContract.View saleView) {
        mTasksRepository = tasksSource;
        mStatsView = saleView;

        mStatsView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void onRefresh() {

        mTasksRepository.getEffectByPage(mPage, mSize, mSelectGroup).subscribe(new Consumer<StatsResp>() {
            @Override
            public void accept(StatsResp statsResp) throws Exception {
                if(statsResp.getResult() == BaseBean.SUCCESS) {
                    List<StatsInfo> infos = statsResp.getData();
                    mStatsView.onRefresh(infos);
                }
            }
        });
    }

    @Override
    public void onLoadMore() {
        mPage+=1;
        mTasksRepository.getEffectByPage(mPage, mSize, mSelectGroup).subscribe(new Consumer<StatsResp>() {
            @Override
            public void accept(StatsResp statsResp) throws Exception {
                if(statsResp.getResult() == BaseBean.SUCCESS) {
                    List<StatsInfo> infos = statsResp.getData();
                    mStatsView.onLoadMore(infos);
                }
            }
        });
    }


    @Override
    public void setSelectedGroups(String groups) {
        mSelectGroup = groups;
    }
}

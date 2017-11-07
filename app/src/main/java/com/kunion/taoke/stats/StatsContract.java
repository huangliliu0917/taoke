package com.kunion.taoke.stats;

import com.kunion.taoke.BasePresenter;
import com.kunion.taoke.BaseView;
import com.kunion.taoke.model.remote.rest.info.SaleInfo;
import com.kunion.taoke.model.remote.rest.info.StatsInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 */

public class StatsContract {

    interface View extends BaseView<Presenter> {
        void onRefresh(List<StatsInfo> infos);
        void onLoadMore(List<StatsInfo> infos);

    }

    interface Presenter extends BasePresenter {
        void onRefresh();
        void onLoadMore();
        void setSelectedGroups(String groups);
    }
}

package com.kunion.taoke.sale;

import android.support.annotation.NonNull;

import com.kunion.taoke.BasePresenter;
import com.kunion.taoke.BaseView;
import com.kunion.taoke.model.remote.rest.info.SaleInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 */

public class SaleContract {

    interface View extends BaseView<Presenter> {
        void onRefresh(List<SaleInfo> infos);
        void onLoadMore(List<SaleInfo> infos);
        void showCount(int count);

    }

    interface Presenter extends BasePresenter {
        void onRefresh();
        void onLoadMore();
        void setSelectedGroups(List<String> groups);
    }
}

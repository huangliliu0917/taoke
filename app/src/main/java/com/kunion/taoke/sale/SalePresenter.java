package com.kunion.taoke.sale;

import android.support.annotation.NonNull;
import android.util.Log;

import com.kunion.taoke.R;
import com.kunion.taoke.TKApp;
import com.kunion.taoke.model.TasksSource;
import com.kunion.taoke.model.remote.rest.BaseBean;
import com.kunion.taoke.model.remote.rest.info.SaleInfo;
import com.kunion.taoke.model.remote.rest.info.UserGroupInfo;
import com.kunion.taoke.model.remote.rest.resp.CheckVersionResp;
import com.kunion.taoke.model.remote.rest.resp.GroupsResp;
import com.kunion.taoke.model.remote.rest.resp.LoginResp;
import com.kunion.taoke.model.remote.rest.resp.SaleResp;
import com.kunion.taoke.model.remote.rest.resp.StringResp;
import com.kunion.taoke.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/10/10.
 */

public class SalePresenter implements SaleContract.Presenter {

    @NonNull
    private TasksSource mTasksRepository;

    @NonNull
    private final SaleContract.View mSaleView;

    private int mPage = 0;
    private int mSize = 10;

    private List<String> mSelectGroups;

    public SalePresenter(@NonNull TasksSource tasksSource, @NonNull SaleContract.View saleView) {
        mTasksRepository = tasksSource;
        mSaleView = saleView;

        mSaleView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void onRefresh() {

        mTasksRepository.getSalesByPage(mPage, mSize, mSelectGroups).subscribe(new Consumer<SaleResp>() {
            @Override
            public void accept(SaleResp saleResp) throws Exception {
                if(saleResp.getResult() == BaseBean.SUCCESS) {
                    List<SaleInfo> infos = saleResp.getData();
                    mSaleView.onRefresh(infos);
                    mSaleView.showCount(saleResp.getTotal());
                }
            }
        });
    }

    @Override
    public void onLoadMore() {
        mPage+=1;
        mTasksRepository.getSalesByPage(mPage, mSize, mSelectGroups).subscribe(new Consumer<SaleResp>() {
            @Override
            public void accept(SaleResp saleResp) throws Exception {
                if(saleResp.getResult() == BaseBean.SUCCESS) {
                    List<SaleInfo> infos = saleResp.getData();
                    mSaleView.onLoadMore(infos);
                    mSaleView.showCount(saleResp.getTotal());
                }
            }
        });
    }


    @Override
    public void setSelectedGroups(List<String> groups) {
        mSelectGroups = groups;
    }
}

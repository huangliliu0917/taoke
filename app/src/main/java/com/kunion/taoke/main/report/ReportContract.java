package com.kunion.taoke.main.report;

import android.view.View;

import com.kunion.taoke.BasePresenter;
import com.kunion.taoke.BaseView;

/**
 * Created by Administrator on 2017/10/10.
 */

public class ReportContract {

    interface ViewPresenter extends BaseView<ReportContract.Presenter> {

        void onCardClick(View view);
    }

    interface Presenter extends BasePresenter {
        void onCardClick(View view);
    }
}

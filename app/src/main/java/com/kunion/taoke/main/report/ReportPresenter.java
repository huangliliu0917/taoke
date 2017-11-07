package com.kunion.taoke.main.report;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.kunion.taoke.R;
import com.kunion.taoke.model.TasksSource;



/**
 * Created by Administrator on 2017/10/11.
 */

public class ReportPresenter implements ReportContract.Presenter {

    @NonNull
    private TasksSource mTasksRepository;

    @NonNull
    private final ReportContract.ViewPresenter mReportView;


    public ReportPresenter(@NonNull TasksSource tasksSource, @NonNull ReportContract.ViewPresenter reportView) {
        mTasksRepository = tasksSource;
        mReportView = reportView;
        mReportView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void onCardClick(View view) {
        mReportView.onCardClick(view);
    }
}

package com.kunion.taoke.stats;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.kunion.taoke.R;
import com.kunion.taoke.model.ModelHelper;
import com.kunion.taoke.util.ActivityUtils;

/**
 * Created by Administrator on 2017/10/10.
 */

public class StatsActivity extends AppCompatActivity{

    private StatsPresenter mStatsPresenter;
    private ActionBar mActionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_act);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);

        mActionBar.setTitle(R.string.report_stats);

        StatsFragment statsFragment = (StatsFragment)getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (statsFragment == null) {
            statsFragment = StatsFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    statsFragment, R.id.contentFrame);
        }

        mStatsPresenter = new StatsPresenter(ModelHelper.providerTasksRepository(), statsFragment);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

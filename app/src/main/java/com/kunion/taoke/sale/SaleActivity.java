package com.kunion.taoke.sale;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.kunion.taoke.R;
import com.kunion.taoke.model.ModelHelper;
import com.kunion.taoke.util.ActivityUtils;

/**
 * Created by Administrator on 2017/10/10.
 */

public class SaleActivity extends AppCompatActivity{

    private SalePresenter mSalePresenter;
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

        mActionBar.setTitle(R.string.report_sales);

        SaleFragment saleFragment = (SaleFragment)getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (saleFragment == null) {
            saleFragment = SaleFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    saleFragment, R.id.contentFrame);
        }

        mSalePresenter = new SalePresenter(ModelHelper.providerTasksRepository(), saleFragment);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

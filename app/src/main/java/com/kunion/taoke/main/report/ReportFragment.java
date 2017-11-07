package com.kunion.taoke.main.report;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kunion.taoke.R;
import com.kunion.taoke.TKApp;
import com.kunion.taoke.databinding.LoginFragBinding;
import com.kunion.taoke.databinding.ReportFragBinding;
import com.kunion.taoke.login.LoginBean;
import com.kunion.taoke.login.LoginContract;
import com.kunion.taoke.login.LoginPresenter;
import com.kunion.taoke.main.MainActivity;
import com.kunion.taoke.sale.SaleActivity;
import com.kunion.taoke.stats.StatsActivity;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/10/10.
 */

public class ReportFragment extends Fragment implements ReportContract.ViewPresenter {

    private ReportContract.Presenter mPresenter;


    @Override
    public void setPresenter(@NonNull ReportContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public static ReportFragment newInstance() {
        return new ReportFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ReportFragBinding binding = DataBindingUtil.inflate(inflater, R.layout.report_frag, container, false);
        binding.setPresenter((ReportPresenter) mPresenter);
        return binding.getRoot();
    }

    @Override
    public void onCardClick(View view) {
        switch (view.getId()) {
            case R.id.report_stats_id:
                startActivity(new Intent(getActivity(), StatsActivity.class));
                break;
            case R.id.report_sale_id:
                startActivity(new Intent(getActivity(), SaleActivity.class));
                break;
            default:
                break;

        }
    }
}

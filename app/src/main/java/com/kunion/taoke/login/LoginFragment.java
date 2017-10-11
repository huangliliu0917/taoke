package com.kunion.taoke.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.util.Preconditions;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kunion.taoke.R;
import com.kunion.taoke.TKApp;
import com.kunion.taoke.databinding.LoginFragBinding;
import com.kunion.taoke.main.MainActivity;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/10/10.
 */

public class LoginFragment extends Fragment implements LoginContract.View {

    private LoginContract.Presenter mPresenter;

    private LoginBean mLoginBean;

    @Override
    public void setPresenter(@NonNull LoginContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab_login);
        fab.setImageResource(R.drawable.ic_done);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LoginFragBinding binding = DataBindingUtil.inflate(inflater, R.layout.login_frag, container, false);
        mLoginBean = new LoginBean();

        mLoginBean.name.set(TKApp.SPUtil.getValue("username", ""));
        mLoginBean.password.set(TKApp.SPUtil.getValue("password", ""));

        binding.setLoginbean(mLoginBean);
        binding.setPresenter((LoginPresenter) mPresenter);
        return binding.getRoot();
    }

    @Override
    public void showLoginSuccess() {
        Snackbar.make(getView(), R.string.login_success, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginFail(int resId) {
        Snackbar.make(getView(), resId, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void goMain() {
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }
}

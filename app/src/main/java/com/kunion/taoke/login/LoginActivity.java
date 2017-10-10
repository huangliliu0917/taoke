package com.kunion.taoke.login;

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

public class LoginActivity extends AppCompatActivity{

    private LoginPresenter mLoginPresenter;
    private ActionBar mActionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_act);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);


        LoginFragment loginFragment = (LoginFragment)getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    loginFragment, R.id.contentFrame);
        }

        mLoginPresenter = new LoginPresenter(ModelHelper.providerTasksRepository(), loginFragment);
    }



}

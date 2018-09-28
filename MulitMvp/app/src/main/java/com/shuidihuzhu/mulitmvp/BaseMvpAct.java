package com.shuidihuzhu.mulitmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class BaseMvpAct<T extends WrapperPresenter> extends AppCompatActivity implements BaseView {
    protected T mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_mvp);
        mPresenter = createP();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }


    public abstract void initialize();

    protected abstract T createP();
    protected abstract int addContentView();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.detachView();
        }
    }
}

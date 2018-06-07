package com.xiaoxin.demo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by Chris Ching on 2017/8/18.
 * MVP activity的基类
 */

public abstract class MVPBaseActivity<V extends IBaseView, P extends IBasePresenter<V>> extends BaseActivity implements IBaseDelegate<V, P> {

    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }

    @NonNull
    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void widgetClick(View v) {

    }

}

package com.xiaoxin.demo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Chris Ching on 2017/8/24.
 */

public abstract class MVPBaseFragment<V extends IBaseView, P extends IBasePresenter<V>> extends BaseFragment implements IBaseDelegate<V, P> {

    private P mPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @NonNull
    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }


}

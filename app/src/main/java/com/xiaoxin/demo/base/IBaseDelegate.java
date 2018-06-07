package com.xiaoxin.demo.base;

import android.support.annotation.NonNull;

/**
 * Created by Chris Ching on 2017/8/18.
 * presenter与activity绑定
 */

public interface IBaseDelegate<V extends IBaseView, P extends IBasePresenter<V>> {

    /**初始化presenter*/
    @NonNull
    P createPresenter();

    /**获取presenter*/
    @NonNull
    P getPresenter();
}

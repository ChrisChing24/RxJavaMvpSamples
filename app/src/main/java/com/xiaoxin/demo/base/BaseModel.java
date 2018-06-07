package com.xiaoxin.demo.base;

/**
 * Created by Chris Ching on 2017/8/18.
 */

public abstract class BaseModel<SubP> {
    protected SubP mPresenter;

    public BaseModel(SubP presenter) {
        this.mPresenter = presenter;
    }
}

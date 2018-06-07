package com.xiaoxin.demo.base;

/**
 * Created by Chris Ching on 2017/8/18.
 * 连接model与view的桥梁基类
 */

public interface IBasePresenter<V extends IBaseView> {

    /**绑定接口*/
    void attachView(V view);

    /**释放接口*/
    void detachView();
}

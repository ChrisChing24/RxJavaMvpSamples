package com.xiaoxin.demo.login.view;

import com.xiaoxin.demo.base.IBaseView;

/**
 * Created by Chris Ching on 2017/8/18.
 */

public interface ILoginView extends IBaseView {
    void loginSuccess();
    void loginFailed();
    void showMessage(String msg);
}

package com.xiaoxin.demo.login.presenter;

import android.content.Context;
import com.xiaoxin.demo.base.IBasePresenter;
import com.xiaoxin.demo.login.model.LoginModel;
import com.xiaoxin.demo.login.view.ILoginView;

/**
 * Created by Chris Ching on 2017/8/18.
 *
 */

public class LoginPresenter implements IBasePresenter<ILoginView> {
    private ILoginView mView;
    private LoginModel mModel;

    public LoginPresenter(ILoginView view) {
        attachView(view);
        mModel = new LoginModel(this);
    }


    @Override
    public void attachView(ILoginView view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    public void loginSuccess() {
        mView.dismiss();
        mView.loginSuccess();
    }

    public void loginFailed() {
        mView.dismiss();
        mView.loginFailed();
    }

    public void showMessage(String msg) {//用于显示信息提示
        mView.showMessage(msg);
    }

    public void login(Context context, String userName, String password) {
        mView.showLoading();
        mModel.login(context, userName, password);
    }
}

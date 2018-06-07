package com.xiaoxin.demo.login.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wang.avi.AVLoadingIndicatorView;
import com.xiaoxin.demo.R;
import com.xiaoxin.demo.base.MVPBaseActivity;
import com.xiaoxin.demo.global.Constant;
import com.xiaoxin.demo.login.presenter.LoginPresenter;
import com.xiaoxin.demo.login.view.ILoginView;
import com.xiaoxin.demo.ui.MainActivity;
import com.xiaoxin.demo.utils.SpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Chris Ching on 2017/8/18.
 * 登录界面
 */

public class LoginActivity extends MVPBaseActivity<ILoginView, LoginPresenter> implements ILoginView {

    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.loading_view)
    AVLoadingIndicatorView mLoadingView;
    private Context mContext;
    private String mUserName;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = LoginActivity.this;
        ButterKnife.bind(this);
        setAllowFullScreen(true);//设置全屏
        setSteepStatusBar(true);//设置沉浸式状态栏
        setScreenRotate(true);

        String userName = SpUtil.getString(mContext, Constant.USERNAME_KEY, "");
        String password = SpUtil.getString(mContext, Constant.PASSWORD_KEY, "");
        mEtUsername.setText(userName);
        mEtPassword.setText(password);
        mEtUsername.setSelection(userName.length());//光标定位到最后
    }

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }


    @Override
    public void loginSuccess() {//登录成功跳到主界面
        SpUtil.saveString(mContext, Constant.USERNAME_KEY, mUserName);
        SpUtil.saveString(mContext, Constant.PASSWORD_KEY, mPassword);
        showToast(mContext, "登录成功");
        startActivity(MainActivity.class);
    }

    @Override
    public void loginFailed() {
//        showToast(mContext, "登录失败");
//        startActivity(ClassroomActivity.class);
    }

    @Override
    public void showMessage(String msg) {
        showToast(mContext, msg);
    }

    @Override
    public void showLoading() {//显示加载中动画
        mLoadingView.setVisibility(View.VISIBLE);
        mLoadingView.show();
    }

    @Override
    public void dismiss() {//隐藏加载中动画
        mLoadingView.hide();
    }


    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        mUserName = mEtUsername.getText().toString();
        mPassword = mEtPassword.getText().toString();

        getPresenter().login(mContext, mUserName, mPassword);

    }

    public static void start(Context context, Class<LoginActivity> loginActivityClass) {
        context.startActivity(new Intent(context, loginActivityClass));
    }
}

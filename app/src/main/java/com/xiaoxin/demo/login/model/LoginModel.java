package com.xiaoxin.demo.login.model;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xiaoxin.demo.base.BaseModel;
import com.xiaoxin.demo.bean.LoginBean;
import com.xiaoxin.demo.global.ApiUtils;
import com.xiaoxin.demo.global.Constant;
import com.xiaoxin.demo.login.presenter.LoginPresenter;
import com.xiaoxin.demo.utils.DeviceUtils;

import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


/**
 * Created by Chris Ching on 2017/8/18.
 */

public class LoginModel extends BaseModel<LoginPresenter> {

    private LoginPresenter mLoginPresenter;

    public LoginModel(LoginPresenter presenter) {
        super(presenter);
        this.mLoginPresenter = presenter;
    }

    public void login(final Context context, String username, String password) {
        if (DeviceUtils.ifAvailable(context)) {//网络判断
            if (isValid(username, password)) {//判空

                //登录接口,回调presenter层
                ApiUtils.login(context, username, password, "android", DeviceUtils.getDeviceId(context))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Observer<ResponseBody>() {

                            @Override
                            public void onError(Throwable e) {
                                mLoginPresenter.showMessage("登录失败");
                                mLoginPresenter.loginFailed();
                            }

                            @Override
                            public void onComplete() {

                            }

                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(ResponseBody responseBody) {
                                try {
                                    String json = responseBody.string();
//                                Log.d("tag", "json = " + json);

                                    JSONObject jsonObject = new JSONObject(json);
                                    if (jsonObject.has("error_response")) { // 是否含有error_response（错误信息）
                                        JSONObject errorResponse;

                                        errorResponse = jsonObject.getJSONObject("error_response");
                                        if (errorResponse != null) {
                                            String errorHint = errorResponse.getString("msg");

                                            Toast.makeText(context, errorHint, Toast.LENGTH_SHORT).show();
                                            mLoginPresenter.loginFailed();
                                        }
                                    } else {
                                        JSONObject response = jsonObject.getJSONObject("response");
                                        Gson gson = new Gson();
                                        LoginBean loginBean = gson.fromJson(response.toString(), LoginBean.class);
                                        Constant.token = loginBean.getToken();
                                        mLoginPresenter.loginSuccess();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        });
            }
        }

    }

    private boolean isValid(String userName, String password) {
        if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(password)) {
            mLoginPresenter.showMessage("用户名或密码不能为空");
            return false;
        }
        if (TextUtils.isEmpty(userName)) {
            mLoginPresenter.showMessage("用户名不能为空");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            mLoginPresenter.showMessage("密码不能为空");
            return false;
        }
        if (password.length() < 6 || password.length() > 20) {
            mLoginPresenter.showMessage("密码长度为6到20位");
            return false;
        }
        return true;
    }
}

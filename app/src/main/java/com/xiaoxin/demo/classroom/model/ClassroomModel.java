package com.xiaoxin.demo.classroom.model;

import android.content.Context;

import com.xiaoxin.demo.base.BaseModel;
import com.xiaoxin.demo.bean.ClassroomBean;
import com.xiaoxin.demo.bean.ContactsBean;
import com.xiaoxin.demo.classroom.presenter.ClassroomPresenter;
import com.xiaoxin.demo.global.ApiUtils;
import com.xiaoxin.demo.utils.DeviceUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Chris Ching on 2017/8/18.
 */

public class ClassroomModel extends BaseModel<ClassroomPresenter> {

    private ClassroomPresenter mPresenter;

    public ClassroomModel(ClassroomPresenter presenter) {
        super(presenter);
        this.mPresenter = presenter;
    }

    public void getClassroom(Context context, int nextId) {
        if (DeviceUtils.ifAvailable(context)) {
            ApiUtils.getList(context, nextId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(new Observer<ClassroomBean>() {

                        @Override
                        public void onError(Throwable e) {
                            mPresenter.showMessage("failed");
                        }

                        @Override
                        public void onComplete() {

                        }

                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ClassroomBean classroomBean) {
                            if (classroomBean != null && classroomBean.getResponse() != null) {

                                mPresenter.showData(classroomBean);
                            } else if (classroomBean != null && classroomBean.getError_response() != null) {

                                ClassroomBean.ErrorResponse error_response = classroomBean.getError_response();
                                String errorHint = String.format("%s\n错误码：%s", error_response.getMsg(), error_response.getCode());
//                                    Toast.makeText(context, errorHint, Toast.LENGTH_LONG).show();
                                mPresenter.showMessage(errorHint);
                            } else {
                                mPresenter.showEmpty();
                            }
                        }
                    });
        }
    }

    public void getContacts(Context context, int type, int nextPage) {
        if (DeviceUtils.ifAvailable(context)) {
            ApiUtils.getContacts(context, type, nextPage)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(new Observer<ContactsBean>() {


                        @Override
                        public void onError(Throwable e) {
                            mPresenter.showMessage("failed");
                        }

                        @Override
                        public void onComplete() {

                        }

                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ContactsBean contactsBean) {
                            if (contactsBean != null && contactsBean.getResponse() != null) {

                                mPresenter.showData(contactsBean);
                            } else if (contactsBean != null && contactsBean.getError_response() != null) {

                                ContactsBean.ErrorResponse error_response = contactsBean.getError_response();
                                String errorHint = String.format("%s\n错误码：%s", error_response.getMsg(), error_response.getCode());
//                                    Toast.makeText(context, errorHint, Toast.LENGTH_LONG).show();
                                mPresenter.showMessage(errorHint);
                            } else {
                                mPresenter.showEmpty();
                            }
                        }
                    });
        }
    }
}

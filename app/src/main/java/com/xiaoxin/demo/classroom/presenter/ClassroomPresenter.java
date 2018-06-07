package com.xiaoxin.demo.classroom.presenter;

import android.content.Context;

import com.xiaoxin.demo.base.IBasePresenter;
import com.xiaoxin.demo.classroom.model.ClassroomModel;
import com.xiaoxin.demo.classroom.view.IClassroomView;

/**
 * Created by Chris Ching on 2017/8/18.
 */

public class ClassroomPresenter implements IBasePresenter<IClassroomView> {
    private IClassroomView mClassroomView;
    private ClassroomModel mModel;

    public ClassroomPresenter(IClassroomView classroomView) {
        attachView(classroomView);
        mModel = new ClassroomModel(this);
    }

    @Override
    public void attachView(IClassroomView view) {
        this.mClassroomView = view;
    }

    @Override
    public void detachView() {
        this.mClassroomView = null;
    }

    public void showMessage(String msg) {//用于显示信息提示
        mClassroomView.showMessage(msg);
    }

    public void showData(Object object) {
        mClassroomView.dismiss();
        mClassroomView.showData(object);
    }

    public void getList(Context context, int nextPage) {
        mClassroomView.showLoading();
        mModel.getClassroom(context, nextPage);
    }

    public void getContacts(Context context, int type, int nextPage) {
        mClassroomView.showLoading();
        mModel.getContacts(context, type, nextPage);
    }

    public void showEmpty() {
        mClassroomView.dismiss();
        mClassroomView.showEmpty();
    }
}

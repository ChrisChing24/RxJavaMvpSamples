package com.xiaoxin.demo.classroom.view;

import com.xiaoxin.demo.base.IBaseView;

/**
 * Created by Chris Ching on 2017/8/18.
 *
 */

public interface IClassroomView extends IBaseView {

    void showData(Object object);
    void showEmpty();

    void showMessage(String msg);
}

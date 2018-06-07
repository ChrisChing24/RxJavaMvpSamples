package com.xiaoxin.demo.global;

import android.content.Context;

import com.xiaoxin.demo.base.ApiBase;
import com.xiaoxin.demo.bean.ClassroomBean;
import com.xiaoxin.demo.bean.ContactsBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Chris Ching on 2017/8/18.
 * 接口实现类
 */

public class ApiUtils extends ApiBase {

    //登录
    public static Observable<ResponseBody> login(Context context, String userName, String password,
                                                 String device, String deviceToken) {

        Map<String, String> map = new HashMap<>();
        map.put("username", userName);
        map.put("password", password);
        map.put("v", "0.1.0");
        map.put("device", device);
        map.put("device_token", deviceToken);
        return getService(context).login(map);
    }

    //获取教室列表
    public static Observable<ClassroomBean> getList(Context context, int nextId) {
        return getService(context).getList(nextId);
    }

    public static Observable<ContactsBean> getContacts(Context context, int type, int nextId) {
        return getService(context).getContacts(type, nextId);
    }
}

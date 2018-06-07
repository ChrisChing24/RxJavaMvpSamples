package com.xiaoxin.demo.base;

import com.xiaoxin.demo.bean.ClassroomBean;
import com.xiaoxin.demo.bean.ContactsBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Chris Ching on 2017/8/18.
 * 接口类
 */

public interface IApiService {
    //登录接口
    @FormUrlEncoded
    @POST("/api/?method=user.login")
    Observable<ResponseBody> login(@FieldMap Map<String, String> map);

    //获取教室列表
    @FormUrlEncoded
    @POST("/api/?method=classroom.classroomList")
    Observable<ClassroomBean> getList(@Field("next_id") int nextId);

    @FormUrlEncoded
    @POST("/api/?method=contact.getContacts")
    Observable<ContactsBean> getContacts(@Field("group_type") int type, @Field("next_id") int nextId);
}

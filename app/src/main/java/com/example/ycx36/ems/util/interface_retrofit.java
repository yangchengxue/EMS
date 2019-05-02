package com.example.ycx36.ems.util;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface interface_retrofit {
    @FormUrlEncoded
    @POST("api/register")
    Observable<registerBean> register(
            @Field("name") String name,
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("c_password") String c_password
    );

    @FormUrlEncoded
    @POST("api/login")
    Observable<loginBean> login(
            @Field("phone") String phone,
            @Field("password") String password
    );

    //获取信息
    @GET("api/get-details")
    Observable<userInfoBean> getUserInfo(
            @Header("Authorization") String authorization
    );

    //修改信息
    @FormUrlEncoded
    @POST("api/mod-details")
    Observable<changeUserInfoBean> changeUserInfo(
            @Header("Authorization") String authorization,
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone") String phone
    );

    //注册硬件编号接口  https://tbox.techroom.com.cn/api/hw/register
    @FormUrlEncoded
    @POST("/api/hw/register")
    Observable<hw_register> hw_register(
            @Field("hw_id") String hw_id
    );


    //检测硬件是否注册接口  https://tbox.techroom.com.cn/api/hw/check
    @GET("/api/hw/check")
    Observable<hw_checkBean> getHwCheck(
            @Query("hw_id") String hw_id
    );

    //获取硬件状态   https://tbox.techroom.com.cn/api/hw/get/{name}
    @GET("/api/hw/get/{name}")
    Observable<getHwStatusBean> getHwStatus(
            @Path("name") String name,
            @Query("hw_id") String hw_id
    );

}

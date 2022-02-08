package com.android.delivery.api;

import com.android.delivery.model.user.LoginRequest;
import com.android.delivery.model.ResponseDto;
import com.android.delivery.model.user.LogoutRequest;
import com.android.delivery.model.user.UpdateUserInfoRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserAPI {

    @POST("/user/login")
    Call<ResponseDto> loginUser(@Body LoginRequest data);


    @GET("/user/me")
    Call<ResponseDto> getUserInfo();

    @GET("/user/auth")
    Call<Long> authUser();

    @POST("/user/logout")
    Call<ResponseDto> logoutUser(@Body LogoutRequest logoutRequest);

    @POST("/user/update/info")
    Call<ResponseDto> updateUserInfo(@Body UpdateUserInfoRequest infoRequest);

}

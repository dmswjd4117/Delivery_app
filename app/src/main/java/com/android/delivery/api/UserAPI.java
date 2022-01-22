package com.android.delivery.api;

import com.android.delivery.model.user.AddressUpdateRequest;
import com.android.delivery.model.user.LoginRequest;
import com.android.delivery.model.Response;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface UserAPI {

    @POST("/user/login")
    Call<Response> loginUser(@Body LoginRequest data);

    @PATCH("/user/address/update")
    Call<Response> updateAddress(@Body AddressUpdateRequest data);

}

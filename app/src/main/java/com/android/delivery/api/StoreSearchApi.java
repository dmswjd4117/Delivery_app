package com.android.delivery.api;


import com.android.delivery.model.ResponseDto;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StoreSearchApi {

    @GET("/stores/search/all")
    Call<ResponseDto> searchAll(@Query("addressCode") String addressCode, @Query("id") int id);
}

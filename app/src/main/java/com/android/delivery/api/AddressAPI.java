package com.android.delivery.api;

import com.android.delivery.model.Response;
import com.android.delivery.model.address.AddressRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AddressAPI {
    @POST("/address/search/road")
    Call<Response> searchAddressByRoadName(@Body AddressRequest addressRequest);

}

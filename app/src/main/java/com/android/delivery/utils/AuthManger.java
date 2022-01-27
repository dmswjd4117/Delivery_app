package com.android.delivery.utils;

import android.util.Log;

import com.android.delivery.api.UserAPI;
import com.android.delivery.model.ResponseDto;
import com.android.delivery.model.user.UserInfoResponse;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthManger {

//    private static String TAG = "AUTH_USER";
//    private static UserAPI userAPI;
//
//    private static boolean authUser(String token){
//        userAPI = getApi(token);
//
//        userAPI.authUser().enqueue(new Callback<ResponseDto>() {
//            @Override
//            public void onResponse(Call<ResponseDto> call, Response<ResponseDto> response) {
//                ResponseDto responseDto = response.body();
//                if(responseDto == null){
//                    Log.e(TAG, "response body is null");
//                    return false;
//                }
//                if(!responseDto.isSuccess()){
//                    Log.e(TAG, responseDto.getError().getMessage());
//                    return false;
//                }
//                Log.i(TAG, responseDto.isSuccess()+" "+token);
//
////                Type type = new TypeToken<UserInfoResponse>(){}.getType();
////                String jsonResult = gson.toJson(responseDto.getResponse());
////                UserInfoResponse userInfoResponse = gson.fromJson(jsonResult, type);
//
////                binding.myPageName.setText(userInfoResponse.getName());
//            }
//
//            @Override
//            public void onFailure(Call<ResponseDto> call, Throwable t) {
//
//            }
//        });
//
//    }
//
//    private static UserAPI getApi(String token) {
//        if(userAPI == null){
//            userAPI = RetrofitClient.createService(UserAPI.class, token);
//        }
//        return userAPI;
//    }
}

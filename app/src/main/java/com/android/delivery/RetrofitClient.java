package com.android.delivery;

import com.android.delivery.interceptor.AuthInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

     private final static String BASE_URL =
             "http://48bc-2001-e60-cd35-8839-f4e0-a23c-6c22-5b90.ngrok.io";

     private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

     private static Retrofit.Builder builder = new Retrofit.Builder()
                       .baseUrl(BASE_URL)
                       .addConverterFactory(GsonConverterFactory.create());

     private static Retrofit retrofit = builder.build();

     public static <S> S createService(Class<S> serviceClass){
          return retrofit.create(serviceClass);
     }


     public static <S> S createService(Class<S> serviceClass, final String token){
          AuthInterceptor authInterceptor = new AuthInterceptor("Bearer "+token);
          if(!httpClient.interceptors().contains(authInterceptor)){
               httpClient.addInterceptor(authInterceptor);
               builder.client(httpClient.build());
               retrofit = builder.build();
          }

          return retrofit.create(serviceClass);
     }


}

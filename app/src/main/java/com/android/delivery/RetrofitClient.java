package com.android.delivery;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
     private final static String BASE_URL =
             "http://ba72-2001-e60-cd32-da97-d8f0-29ab-6236-947e.ngrok.io";
     private static Retrofit retrofit = null;

     private RetrofitClient(){
     }

     public static Retrofit getClient(){
          if(retrofit == null){
               retrofit = new Retrofit.Builder()
                       .baseUrl(BASE_URL)
                       .addConverterFactory(GsonConverterFactory.create())
                       .build();
          }
          return retrofit;
     }


}

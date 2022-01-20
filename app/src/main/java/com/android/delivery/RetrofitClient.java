package com.android.delivery;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
     private final static String BASE_URL =
             "http://7797-2001-e60-cb11-83ff-ec96-61c5-4944-8c5d.ngrok.io";
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

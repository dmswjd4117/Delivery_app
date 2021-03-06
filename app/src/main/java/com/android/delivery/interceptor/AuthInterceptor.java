package com.android.delivery.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private String authToken;

    public AuthInterceptor(String token){
        this.authToken = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request origin = chain.request();
        Request.Builder builder = origin.newBuilder()
                .header("Authorization", authToken);

        Request request = builder.build();
        return chain.proceed(request);
    }
}

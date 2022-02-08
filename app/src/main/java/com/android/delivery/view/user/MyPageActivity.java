package com.android.delivery.view.user;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
 import androidx.appcompat.widget.Toolbar;
import com.android.delivery.R;
import com.android.delivery.api.UserAPI;
import com.android.delivery.databinding.ActivityMyPageDetailBinding;
import com.android.delivery.databinding.ActivityMypageBinding;
import com.android.delivery.model.ResponseDto;
import com.android.delivery.model.user.UserInfoResponse;
import com.android.delivery.utils.PreferenceManger;
import com.android.delivery.utils.RetrofitClient;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPageActivity extends AppCompatActivity {

    private ActivityMypageBinding binding;
    private UserAPI userAPI;
    private String TAG = "MY_PAGE_TAG";
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMypageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setToolBar();
        setView();
    }

    private void setNotLoginView(){
        binding.myPageName.setText("로그인해주세요");
        binding.myPageNameBox.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            this.startActivity(intent);
        });
    }

    private void setLoginView(UserInfoResponse userInfoResponse){
        binding.myPageName.setText(userInfoResponse.getName());
        binding.myPageNameBox.setOnClickListener(view -> {
            Intent intent = new Intent(this, MyPageDetail.class);
            intent.putExtra("name", userInfoResponse.getName());
            intent.putExtra("email", userInfoResponse.getEmail());
            intent.putExtra("image", userInfoResponse.getImage());
            this.startActivity(intent);
        });
    }


    private void setView() {
        String token = PreferenceManger.getString(this, PreferenceManger.AUTH_TOKEN);
        userAPI = RetrofitClient.createService(UserAPI.class, token);
        userAPI.getUserInfo().enqueue(new Callback<ResponseDto>() {
            @Override
            public void onResponse(Call<ResponseDto> call, Response<ResponseDto> response) {
                ResponseDto responseDto = response.body();
                if(responseDto == null){
                    Log.e(TAG, "user response body is null");
                    setNotLoginView();
                    return;
                }
                if(!responseDto.isSuccess()){
                    Log.e(TAG, responseDto.getError().getMessage());
                    setNotLoginView();
                    return;
                }

                Type type = new TypeToken<UserInfoResponse>(){}.getType();
                String jsonResult = gson.toJson(responseDto.getResponse());
                UserInfoResponse userInfoResponse = gson.fromJson(jsonResult, type);
                setLoginView(userInfoResponse);
            }

            @Override
            public void onFailure(Call<ResponseDto> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void setToolBar() {
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        Glide.with(this)
                .load(R.drawable.ic_search_egg)
                .into(binding.myPageImage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.basic_appbar_action, menu);
        return true;
    }
}
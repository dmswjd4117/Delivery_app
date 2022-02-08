package com.android.delivery.view.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.delivery.R;
import com.android.delivery.api.UserAPI;
import com.android.delivery.databinding.ActivityMyPageDetailBinding;
import com.android.delivery.model.ResponseDto;
import com.android.delivery.model.user.LogoutRequest;
import com.android.delivery.model.user.UpdateUserInfoRequest;
import com.android.delivery.model.user.UserInfoResponse;
import com.android.delivery.utils.PreferenceManger;
import com.android.delivery.utils.RetrofitClient;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyPageDetail extends AppCompatActivity {

    private ActivityMyPageDetailBinding binding;
    private UserAPI userAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMyPageDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String name = getIntent().getExtras().getString("name");
        String email = getIntent().getExtras().getString("email");
        String image = getIntent().getExtras().getString("image");
        binding.myPageDetailName.setText(name);
        binding.myPageDetailEmail.setText(email);
        if(image != null){
            Glide.with(this).load(image).into(binding.myPageDetailImage);
        }

        // user api
        String token = PreferenceManger.getString(this, PreferenceManger.AUTH_TOKEN);
        userAPI = RetrofitClient.createService(UserAPI.class, token);

        // tool bar
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        // logout
        setLogout();



    }

    private void setLogout() {
        binding.myPageDetailLogout.setOnClickListener(view -> {
            LogoutRequest logoutRequest = new LogoutRequest();
            logoutRequest.setToken(PreferenceManger.getString(this, PreferenceManger.AUTH_TOKEN));
            userAPI.logoutUser(logoutRequest).enqueue(new Callback<ResponseDto>() {
                @Override
                public void onResponse(Call<ResponseDto> call, Response<ResponseDto> response) {
                    ResponseDto res = response.body();
                    if(res == null) return;
                    if(!res.isSuccess()){
                        Log.e("TAG", res.getError().getMessage());
                    }
                    Toast.makeText(getApplicationContext(), "로그아웃 하였습니다.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MyPageActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<ResponseDto> call, Throwable t) {
                    Log.e("TAG", t.getMessage());
                }
            });
        });
    }


    private void saveUserInfo() {
        String curPassword = binding.myPageDetailCurPassword.getText().toString();
        String newPassword = binding.myPageDetailNewPassword.getText().toString();
        String name = binding.myPageDetailName.getText().toString();

        UpdateUserInfoRequest updateUserInfoRequest = new UpdateUserInfoRequest();
        updateUserInfoRequest.setCurPassword(curPassword);
        updateUserInfoRequest.setNewPassword(newPassword);
        updateUserInfoRequest.setName(name);
        updateUserInfoRequest.setChangePassword(false);

        userAPI.updateUserInfo(updateUserInfoRequest).enqueue(new Callback<ResponseDto>() {
            @Override
            public void onResponse(Call<ResponseDto> call, Response<ResponseDto> response) {
                ResponseDto res = response.body();
                if(res== null)return;
                if(!res.isSuccess()){
                    Log.e("TAG", res.getError().getMessage());
                    Toast.makeText(getApplicationContext(), res.getError().getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(getApplicationContext(), MyPageActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "저장되었습니다", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ResponseDto> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mypage_detail_action, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.myPageDetail_saveItem:
                saveUserInfo();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
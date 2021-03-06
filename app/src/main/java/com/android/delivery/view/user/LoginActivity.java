package com.android.delivery.view.user;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



import com.android.delivery.databinding.ActivityLoginBinding;
import com.android.delivery.model.user.LoginRequest;
import com.android.delivery.model.ResponseDto;
import com.android.delivery.utils.RetrofitClient;
import com.android.delivery.api.UserAPI;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private MaterialButton loginButton;
    private ActivityLoginBinding binding;
    private Retrofit retrofit;
    private UserAPI userAPI;

    @SuppressLint({"WrongViewCast", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginButton = (MaterialButton) binding.loginButton;
        loginButton.setOnClickListener((view)->{

            String email = binding.loginEmail.getEditText().getText().toString();
            String password = binding.loginPassword.getEditText().getText().toString();

            if((email.length() == 0) ||(password.length() == 0)){
                Toast.makeText(getApplicationContext(), "이메일 또는 비밀번호칸을 채워주세요.",Toast.LENGTH_SHORT).show();
                return;
            }

            LoginRequest loginRequest = new LoginRequest(email, password);

            userAPI = RetrofitClient.createService(UserAPI.class);

            userAPI.loginUser(loginRequest).enqueue(new Callback<ResponseDto>() {
                @Override
                public void onResponse(Call<ResponseDto> call, retrofit2.Response<ResponseDto> response) {
                    ResponseDto res = response.body();
                    if(res == null)return;
                    if(res.isSuccess()){
                        String token = (String) res.getResponse();
                        Log.i("login success", token);


                        SharedPreferences sharedPreferences = getSharedPreferences("preference", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("loginToken", token);
                        editor.commit();

                    }else{
                        Log.e("login fail", res.getError().getMessage());
                        Toast.makeText(getApplicationContext(), res.getError().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }



                @Override
                public void onFailure(Call<ResponseDto> call, Throwable t) {
                }
            });

        });

    }
}

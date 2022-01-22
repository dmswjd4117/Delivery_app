package com.android.delivery.view.address;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.delivery.R;
import com.android.delivery.RetrofitClient;
import com.android.delivery.api.UserAPI;
import com.android.delivery.databinding.ActivityAddressDetailBinding;
import com.android.delivery.model.Response;
import com.android.delivery.model.user.AddressUpdateRequest;

import retrofit2.Call;
import retrofit2.Callback;

public class AddressDetailActivity extends AppCompatActivity {

    private ActivityAddressDetailBinding binding;
    private UserAPI userAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getSharedPreferences("preference", MODE_PRIVATE);

        binding = ActivityAddressDetailBinding.inflate(getLayoutInflater());
        userAPI = RetrofitClient.createService(UserAPI.class, preferences.getString("loginToken", ""));
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String title = intent.getExtras().getString("title");
        String roadNameAddress = intent.getExtras().getString("roadNameAddress");
        String buildingManagementNum = intent.getExtras().getString("buildingManagementNum");


        binding.addressDetailTitle.setText(title);
        binding.addressDetailRoadNameAddress.setText(roadNameAddress);

        binding.addressDetailFinishButton.setOnClickListener(view -> {
            String detailAddress = binding.addressDetailDetailAddress.getText().toString();
            AddressUpdateRequest request = new AddressUpdateRequest(buildingManagementNum, detailAddress);
            userAPI.updateAddress(request).enqueue(new Callback<Response>(){
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                    if(response==null) return;
                    Response body = response.body();

                    if(!body.isSuccess() && body.getError() != null){
                        Log.e("ADDRESS_DETAIL_SETTING", body.getError().getMessage());
                        return;
                    }

                    Toast.makeText(getApplicationContext(), "주소 변경 완료", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    Log.e("ADDRESS_DETAIL_SETTING", t.getMessage());
                }
            });
        });

    }
}
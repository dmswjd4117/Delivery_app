package com.android.delivery.view.address;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.delivery.api.AddressAPI;
import com.android.delivery.RetrofitClient;
import com.android.delivery.databinding.ActivityAddressSettingBinding;
import com.android.delivery.model.Response;
import com.android.delivery.model.address.AddressRequest;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class AddressSettingActivity extends AppCompatActivity {

    private ActivityAddressSettingBinding binding;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddressSettingBinding.inflate(getLayoutInflater());
        retrofit = RetrofitClient.getClient();
        AddressAPI addressAPI = retrofit.create(AddressAPI.class);

        setContentView(binding.getRoot());

        binding.addressSearchSearchBtn.setOnClickListener((view) -> {
            String cityCountyName = binding.addressSearchCityCountyName.getText().toString();
            String roadName = binding.addressSearchBuildingName.getText().toString();
            String buildingName = binding.addressSearchBuildingName.getText().toString();
            String cityName = binding.addressSearchCityName.getText().toString();

            AddressRequest addressRequest = new AddressRequest(cityName, cityCountyName, roadName, buildingName);

            addressAPI.searchAddressByRoadName(addressRequest).enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    Response res = response.body();
                    if(res == null)return;

                    if(res.isSuccess()){
                        List<Object> list = Arrays.asList(res.getResponse());

                        for (int i = 0; i <list.size(); i++) {
                            Log.i("address_info", list.get(i).toString());
                        }
                        Log.i("address_info", "success"+" ");
                    }
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {

                }

            });


        });
    }
}
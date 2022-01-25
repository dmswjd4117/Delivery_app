package com.android.delivery.view.address;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.delivery.adapter.address.AddressSearchAdapter;
import com.android.delivery.adapter.address.AddressSearchItem;
import com.android.delivery.api.AddressAPI;
import com.android.delivery.utils.RetrofitClient;
import com.android.delivery.databinding.ActivityAddressSettingBinding;
import com.android.delivery.model.ResponseDto;
import com.android.delivery.model.address.AddressRequest;
import com.android.delivery.model.address.AddressResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class AddressSettingActivity extends AppCompatActivity {

    private ActivityAddressSettingBinding binding;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddressSettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listView = binding.addressSearchListView;
        AddressSearchAdapter addressSearchAdapter = new AddressSearchAdapter(AddressSettingActivity.this);
        listView.setAdapter(addressSearchAdapter);



        AddressAPI addressAPI = RetrofitClient.createService(AddressAPI.class);
        binding.addressSearchSearchBtn.setOnClickListener((view) -> {
            String cityCountyName = binding.addressSearchCityCountyName.getText().toString();
            String roadName = binding.addressSearchRoadName.getText().toString();
            String buildingName = binding.addressSearchBuildingName.getText().toString();
            String cityName = binding.addressSearchCityName.getText().toString();

            AddressRequest addressRequest = new AddressRequest(cityName, cityCountyName, roadName, buildingName);
            addressAPI.searchAddressByRoadName(addressRequest).enqueue(new Callback<ResponseDto>() {
                @Override
                public void onResponse(Call<ResponseDto> call, retrofit2.Response<ResponseDto> response) {
                    ResponseDto res = response.body();

                    if(res == null)return;

                    if(!res.isSuccess())return;


                    Gson gson = new Gson();
                    Type type = new TypeToken<List<AddressResponse>>(){}.getType();
                    String jsonResult = gson.toJson(res.getResponse());

                    addressSearchAdapter.removeAll();

                    List<AddressResponse> addressResponseList = gson.fromJson(jsonResult, type);
                    for (int i = 0; i < addressResponseList.size(); i++) {
                        AddressResponse addressResponse = addressResponseList.get(i);
                        addressSearchAdapter.addItem(addressResponse.getBuildingNameForCity(), AddressResponse.getRoadNameAddress(addressResponse), addressResponse.getBuildingManagementNum());
                    }

                    addressSearchAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<ResponseDto> call, Throwable t) {
                    Log.e("address_info_error", t.getMessage());
                }

            });


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    AddressSearchItem item = (AddressSearchItem) addressSearchAdapter.getItem(i);

                    Intent intent = new Intent(getApplicationContext(), AddressDetailActivity.class);
                    intent.putExtra("title", item.getTitle());
                    intent.putExtra("roadNameAddress", item.getRoadNameAddress());
                    intent.putExtra("buildingManagementNum", item.getBuildingManagementNum());

                    startActivity(intent);
                }
            });

        });
    }
}



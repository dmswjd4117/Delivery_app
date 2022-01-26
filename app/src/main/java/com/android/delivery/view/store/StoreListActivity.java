package com.android.delivery.view.store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.delivery.adapter.store.StoreAdapter;
import com.android.delivery.api.StoreSearchApi;
import com.android.delivery.databinding.ActivityStoreListActvitiyBinding;
import com.android.delivery.model.ResponseDto;
import com.android.delivery.model.store.StoreInfoDto;
import com.android.delivery.utils.PreferenceManger;
import com.android.delivery.utils.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreListActivity extends AppCompatActivity {

    private ActivityStoreListActvitiyBinding binding;
    private ListView listView;
    private StoreAdapter storeAdapter;
    private StoreSearchApi storeSearchApi;
    private final String TAG = "STORE_LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStoreListActvitiyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listView = binding.storeListView;
        storeAdapter = new StoreAdapter(StoreListActivity.this);
        listView.setAdapter(storeAdapter);

        String addressCode = PreferenceManger.getString(this,"addressCode");
        String categoryId = getIntent().getExtras().getString("category_id");

        storeSearchApi = RetrofitClient.createService(StoreSearchApi.class);
        storeSearchApi.searchAll(addressCode, categoryId).enqueue(new Callback<ResponseDto>() {
            @Override
            public void onResponse(Call<ResponseDto> call, Response<ResponseDto> response) {

                ResponseDto responseDto = response.body();
                if(responseDto == null){
                    Log.e(TAG, "response body is null");
                    return;
                };
                if(!responseDto.isSuccess()){
                    Log.e(TAG, responseDto.getError().getMessage());
                    return;
                }

                Gson gson = new Gson();
                Type type = new TypeToken<List<StoreInfoDto>>(){}.getType();
                String jsonResult = gson.toJson(responseDto.getResponse());
                List<StoreInfoDto> storeInfoList = gson.fromJson(jsonResult, type);

                storeAdapter.removeAll();

                for(StoreInfoDto storeInfoDto: storeInfoList){
                    Log.i(TAG, storeInfoDto.getName()+" "+storeInfoDto.getAddress());
                    storeAdapter.addItem(storeInfoDto);
                }

                storeAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponseDto> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });

    }

}
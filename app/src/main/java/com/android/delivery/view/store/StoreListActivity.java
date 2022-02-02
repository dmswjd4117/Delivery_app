package com.android.delivery.view.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.delivery.R;
import com.android.delivery.adapter.store.StoreAdapter;
import com.android.delivery.adapter.store.StoreItem;
import com.android.delivery.api.StoreSearchApi;
import com.android.delivery.databinding.ActivityStoreListActvitiyBinding;
import com.android.delivery.model.ResponseDto;
import com.android.delivery.model.store.StoreInfoDto;
import com.android.delivery.utils.PreferenceManger;
import com.android.delivery.utils.RetrofitClient;
import com.android.delivery.utils.ToolBarSetting;
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
    private Toolbar toolbar;
    private ToolBarSetting toolBarSetting;
    private Gson gson = new Gson();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStoreListActvitiyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolbar = binding.toolbar;
        toolBarSetting = new ToolBarSetting(toolbar);
        setSupportActionBar(toolbar);
        toolBarSetting.setAddress(getSupportActionBar(), this);


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
                    Log.e(TAG, addressCode+" "+categoryId+" response body is null");
                    return;
                };
                if(!responseDto.isSuccess()){
                    Log.e(TAG, responseDto.getError().getMessage());
                    return;
                }

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


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                StoreItem item = (StoreItem) storeAdapter.getItem(i);

                Intent intent = new Intent(getApplicationContext(), StoreActivity.class);
                intent.putExtra("storeId", item.getId());

                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.basic_appbar_action, menu);
        return true;
    }



}
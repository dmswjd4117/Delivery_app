package com.android.delivery.view.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.delivery.R;
import com.android.delivery.adapter.option.OptionAdapter;
import com.android.delivery.adapter.option.OptionItem;
import com.android.delivery.api.MenuApi;
import com.android.delivery.databinding.ActivityMenuBinding;
import com.android.delivery.model.menu.GroupMenuDto;
import com.android.delivery.model.menu.OptionDto;
import com.android.delivery.utils.RetrofitClient;
import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MenuActivity extends AppCompatActivity {

    private Long menuId, price;
    private String name, description, photo;
    private String TAG = "MENU_DETAIL";

    private ActivityMenuBinding binding ;
    private MenuApi menuApi ;
    private OptionAdapter optionAdapter;
    private ListView listView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMenuBinding.inflate(this.getLayoutInflater());
        menuApi =  RetrofitClient.createService(MenuApi.class);
        optionAdapter = new OptionAdapter(this);
        listView = binding.menuDetailOptionListView; // ~
        toolbar = binding.toolbar;


        setContentView(binding.getRoot());
        listView.setAdapter(optionAdapter);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);


        initView();


        binding.menuDetailOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Long> checkedItemList = optionAdapter.getCheckedItems();
                for(Long id: checkedItemList){
                    Log.i(TAG, id+"선택");
                }
            }
        });

    }

    private void initView() {
        Intent intent = getIntent();
        menuId = intent.getLongExtra("menuId",  -1L);
        name = intent.getStringExtra("name");
        photo = intent.getStringExtra("photo");
        description = intent.getStringExtra("description");
        price = intent.getLongExtra("price", 0);


        binding.menuDetailDescription.setText(description);
        binding.menuDetailName.setText(name);
        binding.menuDetailPrice.setText(price+"");
        if(!photo.equals("")){
            Glide.with(this).load(photo).into(binding.menuDetailImage);
        }


        menuApi.getOptionList(menuId).enqueue(new Callback<List<OptionDto>>() {
            @Override
            public void onResponse(Call<List<OptionDto>> call, Response<List<OptionDto>> response) {
                List<OptionDto> list = response.body();
                if(list == null){
                    Log.e(TAG, "OptionDto list is null");
                    return;
                }
                for (int i = 0; i < list.size(); i++) {
                    OptionDto option = list.get(i);
                    Log.i(TAG, option.getName());
                    optionAdapter.addItem(option);
                }

                optionAdapter.notifyDataSetChanged();
                Log.i(TAG, optionAdapter.getCount()+" !!");
                setListViewHeight();
            }

            @Override
            public void onFailure(Call<List<OptionDto>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.basic_appbar_action, menu);
        return true;
    }

    private void setListViewHeight(){
        int result = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for(int i=0; i<optionAdapter.getCount(); i++){
            View view = optionAdapter.getView(i, null, listView);
            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            result += view.getMeasuredHeight();
        }


        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = result;

        listView.setLayoutParams(params);
        listView.requestLayout();

    }

}


package com.android.delivery.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.delivery.R;
import com.android.delivery.api.StoreSearchApi;
import com.android.delivery.databinding.ActivityMainBinding;
import com.android.delivery.model.ResponseDto;
import com.android.delivery.model.store.StoreInfoDto;
import com.android.delivery.utils.PreferenceManger;
import com.android.delivery.utils.RetrofitClient;
import com.android.delivery.view.address.AddressSettingActivity;
import com.android.delivery.view.store.StoreListActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private StoreSearchApi storeSearchApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        storeSearchApi = RetrofitClient.createService(StoreSearchApi.class);

        // tool bar
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);

        String title = PreferenceManger.getString(this, "addressTitle")+" ▼";
        if(title.equals("")){
            PreferenceManger.setString(this, "addressTitle", "부경대학교");
            PreferenceManger.setString(this, "addressCode", "2629010600");
            PreferenceManger.setString(this,"detailAddress", "청운관");
            PreferenceManger.setString(this, "roadNameAddress", "수영로358번길");
            title = "부경대학교 ▼";
        }

        actionBar.setTitle(title);
        toolbar.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddressSettingActivity.class);
            startActivity(intent);
        });

        // menu category
        GridLayout gridLayout = binding.menuBarGridLayout;
        int childCnt = gridLayout.getChildCount();

        Button[] buttons = new Button[childCnt+2];
        for (int i = 1; i <= childCnt; i++) {
            int id = getResources().getIdentifier("category_"+i, "id", getPackageName());
            buttons[i] = (Button) findViewById(id);
        }
        for(int i= 1;  i <= childCnt; i++){
            Button button = buttons[i];
            int categoryId = i;
            button.setOnClickListener((view)->{
                Intent intent = new Intent(getApplicationContext(), StoreListActivity.class);
                intent.putExtra("category_id", categoryId+"");
                startActivity(intent);
            });
        }


    }


    // https://lktprogrammer.tistory.com/167 tool bar's menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.appbar_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
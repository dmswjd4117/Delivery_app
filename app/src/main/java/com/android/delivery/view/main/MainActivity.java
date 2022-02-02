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
import com.android.delivery.utils.ToolBarSetting;
import com.android.delivery.view.address.AddressSettingActivity;
import com.android.delivery.view.store.StoreListActivity;
import com.android.delivery.view.user.MyPage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ToolBarSetting toolBarSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // tool bar
        Toolbar toolbar = binding.toolbar;
        toolBarSetting = new ToolBarSetting(toolbar);
        setSupportActionBar(toolbar);
        toolBarSetting.setAddress(getSupportActionBar(), this);


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
            case R.id.menuSearch_myPage:
                Intent intent = new Intent(getApplicationContext(), MyPage.class);
                startActivity(intent);

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
package com.android.delivery.view.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.android.delivery.R;
import com.android.delivery.databinding.ActivityStoreListActvitiyBinding;
import com.android.delivery.utils.ToolBarSetting;

public class StoreActivity extends AppCompatActivity {

    private ActivityStoreListActvitiyBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStoreListActvitiyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }

}
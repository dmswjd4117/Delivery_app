package com.android.delivery.view.cart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.delivery.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity {

    ActivityCartBinding cartBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartBinding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(cartBinding.getRoot());


    }
}
package com.app.orderfood.shop;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.app.orderfood.databinding.ActivityShopMainBinding;

public class ShopMainActivity extends AppCompatActivity {
    ActivityShopMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShopMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }


}
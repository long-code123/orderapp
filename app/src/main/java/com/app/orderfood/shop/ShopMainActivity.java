package com.app.orderfood.shop;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.app.orderfood.databinding.ActivityMainBinding;
import com.app.orderfood.databinding.ActivityShopMainBinding;
import com.app.orderfood.fb.shop.ShopUtils;
import com.app.orderfood.models.Category;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference;

class ShopMainActivity extends AppCompatActivity {
    ActivityShopMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShopMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initLocation();

    }

    private void initLocation() {
//        DatabaseReference myred = database.get
    }
}
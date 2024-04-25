package com.app.orderfood.shop;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.app.orderfood.Adapter.BestFoodsAdapter;
import com.app.orderfood.databinding.ActivityMainBinding;
import com.app.orderfood.databinding.ActivityShopMainBinding;
import com.app.orderfood.fb.shop.ShopUtils;
import com.app.orderfood.models.Category;
import com.app.orderfood.models.Foods;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShopMainActivity extends AppCompatActivity {
    ActivityShopMainBinding binding;

    BestFoodsAdapter bestFoodsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShopMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initLocation();

        bestFoodsAdapter = new BestFoodsAdapter(new ArrayList());

        initView();

        initData();

    }

    private void initView() {
        binding.bestFoodView.setAdapter(bestFoodsAdapter);
    }

    private void initData() {
        binding.progressBarBestFood.setVisibility(View.VISIBLE);
        FirebaseDatabase.getInstance().getReference("Foods")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        ArrayList<Foods> arrayList = new ArrayList<>();

                        for (DataSnapshot item : snapshot.getChildren()) {
                            Foods itemData = item.getValue(Foods.class);
                            arrayList.add(itemData);
                        }

                        Log.e("truongpa", "getAllCategory - Success");

                        for (Foods category : arrayList) {
                            Log.e("truongpa", "" + category.getId() + " - " + category.getTitle());
                        }
                        bestFoodsAdapter.setData(arrayList);
                        binding.progressBarBestFood.setVisibility(View.GONE);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("truongpa", "getAllCategory - Fail");
                        binding.progressBarBestFood.setVisibility(View.GONE);
                    }
                });

    }

    private void initLocation() {
//        DatabaseReference myred = database.get
    }
}
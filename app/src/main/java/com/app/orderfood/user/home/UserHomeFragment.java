package com.app.orderfood.user.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.orderfood.Adapter.bestfood.BestFoodsAdapter;
import com.app.orderfood.Adapter.bestfood.CategoryAdapter;
import com.app.orderfood.Adapter.bestfood.OnClickBestFood;
import com.app.orderfood.Adapter.bestfood.OnClickCategory;
import com.app.orderfood.databinding.FragmentUserHomeBinding;
import com.app.orderfood.models.Category;
import com.app.orderfood.models.Foods;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserHomeFragment extends Fragment {

    FragmentUserHomeBinding binding;
    BestFoodsAdapter bestFoodsAdapter;
    CategoryAdapter categoryAdapter;

    public UserHomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Code từ đây code đi
        /*FirebaseDatabase.getInstance().getReference("Category")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        ArrayList<Category> arrayList = new ArrayList<>();

                        for (DataSnapshot item : snapshot.getChildren()) {
                            Category itemData = item.getValue(Category.class);

                            arrayList.add(itemData);
                        }

                        *//*Log.e("truongpa", "getAllCategory - Success");

                        for (Category category : arrayList) {
                            Log.e("truongpa", "" + category.getId() + " - " + category.getName());
                        }*//*

                        //Show data
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("truongpa", "getAllCategory - Fail");
                    }
                });*/
        bestFoodsAdapter = new BestFoodsAdapter(new ArrayList(), new OnClickBestFood() {
            @Override
            public void clickItem(Foods foods, int position) {
                Toast.makeText(requireContext(), position + " - " + foods.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        categoryAdapter = new CategoryAdapter(new ArrayList(), new OnClickCategory() {
            @Override
            public void clickItem(Category category, int position) {
                Toast.makeText(requireContext(), position + " - " + category.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        initViewBestFood();
        initDataBestFood();
        initViewCategory();
        initDataCategory();
    }

    private void initViewBestFood() {
        binding.bestFoodView.setAdapter(bestFoodsAdapter);
    }

    private void initDataBestFood() {
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
    private void initViewCategory() {
        binding.categoryView.setAdapter(categoryAdapter);
    }
    private void initDataCategory() {
            FirebaseDatabase.getInstance().getReference("Category")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            ArrayList<Category> arrayList = new ArrayList<>();

                            for (DataSnapshot item : snapshot.getChildren()) {
                                Category itemData = item.getValue(Category.class);
                                arrayList.add(itemData);
                            }

                            Log.e("truongpa", "getAllCategory - Success");

                            for (Category category : arrayList) {
                                Log.e("truongpa", "" + category.getId() + " - " + category.getName());
                            }
                            categoryAdapter.setData(arrayList);
                            binding.progressBarCategory.setVisibility(View.GONE);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.e("truongpa", "getAllCategory - Fail");
                            binding.progressBarCategory.setVisibility(View.GONE);
                        }
                    });

        }




    private void initLocation() {
//        DatabaseReference myred = database.get
    }
}
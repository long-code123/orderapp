package com.app.orderfood.user.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.orderfood.R;
import com.app.orderfood.databinding.FragmentUserHomeBinding;
import com.app.orderfood.models.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserHomeFragment extends Fragment {

    FragmentUserHomeBinding binding;

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
        FirebaseDatabase.getInstance().getReference("Category")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        ArrayList<Category> arrayList = new ArrayList<>();

                        for (DataSnapshot item : snapshot.getChildren()) {
                            Category itemData = item.getValue(Category.class);

                            arrayList.add(itemData);
                        }

                        /*Log.e("truongpa", "getAllCategory - Success");

                        for (Category category : arrayList) {
                            Log.e("truongpa", "" + category.getId() + " - " + category.getName());
                        }*/

                        //Show data
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("truongpa", "getAllCategory - Fail");
                    }
                });
    }
}
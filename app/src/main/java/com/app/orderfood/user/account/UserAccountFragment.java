package com.app.orderfood.user.account;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.orderfood.databinding.FragmentUserAccountBinding;
import com.app.orderfood.fb.Utils;
import com.app.orderfood.models.User;
import com.bumptech.glide.Glide;
import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class UserAccountFragment extends Fragment {

    FragmentUserAccountBinding binding;

    public UserAccountFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserAccountBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseDatabase.getInstance().getReference().child("user").child(Utils.getUID())
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Lấy dữ liệu từ dataSnapshot
                    User user = dataSnapshot.getValue(User.class);
                    String photoUrl = user.getLinkAvt();
                    String name = user.getName();
                    String address = user.getAddress();
                    String phone = user.getPhone();
                    Glide.with(requireContext()).load(photoUrl).into(binding.imgAvt);
                    binding.tvname.setText("Name: " + name);
                    binding.tvaddress.setText("Address: " + address);
                    binding.tvphone.setText("Phone Number: " + phone);

                } else {
                    Log.e("long", "Nothing");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("long", "Fail");
            }
        });
    }

}
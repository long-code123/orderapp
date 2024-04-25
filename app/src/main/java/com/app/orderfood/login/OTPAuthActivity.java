package com.app.orderfood.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.app.orderfood.databinding.ActivityOtpauthBinding;
import com.app.orderfood.models.User;
import com.app.orderfood.shiper.ShipperMainActivity;
import com.app.orderfood.shop.ShopMainActivity;
import com.app.orderfood.user.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class OTPAuthActivity extends AppCompatActivity {

    ActivityOtpauthBinding binding;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpauthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();

        binding.btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = binding.edtOTP.getText().toString();

                String code = getIntent().getStringExtra("code");

                if (code.isEmpty()) {
                    Toast.makeText(OTPAuthActivity.this, "Chưa nhập OTP!", Toast.LENGTH_SHORT).show();
                    return;
                }

                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(code, otp);

                firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String userID = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                            FirebaseDatabase.getInstance().getReference("user")
                                    .child(userID).get()
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            DataSnapshot result = task1.getResult();
                                            if (result.exists()) {
                                                User user = result.getValue(User.class);
                                                Toast.makeText(OTPAuthActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent();
                                                switch (user.getRole()) {
                                                    case 1:
                                                        intent = new Intent(OTPAuthActivity.this, MainActivity.class);
                                                        break;
                                                    case 2:
                                                        intent = new Intent(OTPAuthActivity.this, ShopMainActivity.class);
                                                        break;
                                                    case 3:
                                                        intent = new Intent(OTPAuthActivity.this, ShipperMainActivity.class);
                                                        break;
                                                }
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                Intent intent = new Intent(OTPAuthActivity.this, RegisterInfoActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                            }
                                        } else {
                                            Toast.makeText(OTPAuthActivity.this, "Đăng nhập không thành công!", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(OTPAuthActivity.this, "Đăng nhập không thành công!", Toast.LENGTH_SHORT).show();
                                    });
                        } else {
                            Toast.makeText(OTPAuthActivity.this, "Đăng nhập không thành công!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
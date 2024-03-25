package com.app.orderfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.app.orderfood.databinding.ActivityOtpauthBinding;
import com.app.orderfood.user.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

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

                PhoneAuthCredential credential = PhoneAuthProvider.getCredential( code, otp);

                firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(OTPAuthActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(OTPAuthActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else  {
                            Toast.makeText(OTPAuthActivity.this, "Đăng nhập thaais bại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
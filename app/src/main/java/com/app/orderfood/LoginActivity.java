package com.app.orderfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.orderfood.databinding.ActivityLoginBinding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Force reCAPTCHA flow
        FirebaseAuth.getInstance().getFirebaseAuthSettings().forceRecaptchaFlowForTesting(true);

        firebaseAuth = FirebaseAuth.getInstance();

        binding.btnSendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = binding.edtNumber.getText().toString().replace(" ", "");
                String countryCode = binding.countryCodePicker.getSelectedCountryCode();

                if (phoneNumber.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Nhập số điện thoại!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String phone = "+" + countryCode + phoneNumber;

                Toast.makeText(LoginActivity.this, phone, Toast.LENGTH_SHORT).show();

                PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phone)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(LoginActivity.this)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                Toast.makeText(LoginActivity.this, "onVerificationCompleted", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(LoginActivity.this, "onVerificationFailed", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                Toast.makeText(LoginActivity.this, "OTP đã gửi!!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, OTPAuthActivity.class);
                                intent.putExtra("code", s);
                                startActivity(intent);
                            }
                        })
                        .build();

                PhoneAuthProvider.verifyPhoneNumber(options);
            }
        });

    }
}
package com.app.orderfood;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.orderfood.fb.Utils;
import com.app.orderfood.login.LoginActivity;
import com.app.orderfood.login.OTPAuthActivity;
import com.app.orderfood.login.RegisterInfoActivity;
import com.app.orderfood.models.User;
import com.app.orderfood.shiper.ShipperMainActivity;
import com.app.orderfood.shop.ShopMainActivity;
import com.app.orderfood.user.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebaseAuth = FirebaseAuth.getInstance();

        checkLogin();


        /*ShopUtils.insertCategory(
                new Category(
                        Utils.getNewId(), "khgffsdhkgfhd", "Cơm rang"
                )
        );*/


        //ShopUtils.getAllCategoryListener();
        //ShopUtils.deleteCategory(0);

        //FirebaseAuth.getInstance().signOut();
    }

    private void checkLogin() {
        if (firebaseAuth.getCurrentUser() != null) {
            String userID = firebaseAuth.getCurrentUser().getUid();
            FirebaseDatabase.getInstance().getReference("user")
                    .child(userID).get()
                    .addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            DataSnapshot result = task1.getResult();
                            if (result.exists()) {
                                User user = result.getValue(User.class);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(SplashActivity.this, "Welcome back!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent();
                                        switch (user.getRole()) {
                                            case 1:
                                                intent = new Intent(SplashActivity.this, MainActivity.class);
                                                break;
                                            case 2:
                                                intent = new Intent(SplashActivity.this, ShopMainActivity.class);
                                                break;
                                            case 3:
                                                intent = new Intent(SplashActivity.this, ShipperMainActivity.class);
                                                break;
                                        }
                                        startActivity(intent);
                                        finish();
                                    }
                                }, 2000L);
                            } else {
                                Intent intent = new Intent(SplashActivity.this, RegisterInfoActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        } else {
                            startLogin();
                        }
                    })
                    .addOnFailureListener(e -> {
                        startLogin();
                    });
        } else {
            startLogin();
        }
    }

    private void startLogin() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, 2000L);

    }

    @Override
    @SuppressLint("MissingSuperCall")
    public void onBackPressed() {
    }
}
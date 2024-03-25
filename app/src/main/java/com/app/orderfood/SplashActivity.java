package com.app.orderfood;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.app.orderfood.user.MainActivity;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebaseAuth = FirebaseAuth.getInstance();

        checkLogin();

        //FirebaseAuth.getInstance().signOut();
    }

    private void checkLogin() {
        if (firebaseAuth.getCurrentUser()!= null) {
            startMain();
        } else {
            startLogin();
        }
    }

    private void  startMain(){
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    private void startLogin() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    @SuppressLint("MissingSuperCall")
    public void onBackPressed() { }
}
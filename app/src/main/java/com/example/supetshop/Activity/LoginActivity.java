package com.example.supetshop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.supetshop.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void kayit(View view) {
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
    }

    public void giris(View view) {
        startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
    }
}
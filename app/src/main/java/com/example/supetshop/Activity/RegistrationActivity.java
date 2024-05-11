package com.example.supetshop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.supetshop.R;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void kayit(View view) {
        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
    }

    public void giris(View view) {
        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    }
}
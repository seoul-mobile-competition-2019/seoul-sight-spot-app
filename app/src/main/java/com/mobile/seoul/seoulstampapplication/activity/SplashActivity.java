package com.mobile.seoul.seoulstampapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mobile.seoul.seoulstampapplication.MainActivity;
import com.mobile.seoul.seoulstampapplication.R;


public class SplashActivity extends AppCompatActivity {

    private final long SPLASH_DISPLAY_TIME = 1000L;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(()-> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }, SPLASH_DISPLAY_TIME);
    }
}

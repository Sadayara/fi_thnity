package tn.esprit.fi_thnity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import tn.esprit.fi_thnity.R;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 2500; // 2.5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Hide action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Navigate to Onboarding after delay
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            // TODO: Check if user has seen onboarding before (SharedPreferences)
            // TODO: Check if user is logged in (Firebase Auth)
            // For now, go to OnboardingActivity
            Intent intent = new Intent(SplashActivity.this, OnboardingActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_DURATION);
    }
}

package tn.esprit.fi_thnity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import tn.esprit.fi_thnity.R;

public class OTPVerificationActivity extends AppCompatActivity {

    private TextView tvPhoneNumber, tvResendTimer;
    private TextInputEditText etOTP;
    private MaterialButton btnVerify, btnResend;
    private ImageButton btnBack;
    private ProgressBar progressBar;

    private String phoneNumber;
    private CountDownTimer countDownTimer;
    private static final long RESEND_TIMEOUT = 60000; // 60 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        // Get phone number from intent
        phoneNumber = getIntent().getStringExtra("phoneNumber");

        // Initialize views
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber);
        tvResendTimer = findViewById(R.id.tvResendTimer);
        etOTP = findViewById(R.id.etOTP);
        btnVerify = findViewById(R.id.btnVerify);
        btnResend = findViewById(R.id.btnResend);
        btnBack = findViewById(R.id.btnBack);
        progressBar = findViewById(R.id.progressBar);

        // Display phone number
        tvPhoneNumber.setText(getString(R.string.otp_sent_to, phoneNumber));

        // Start resend timer
        startResendTimer();

        // Back button
        btnBack.setOnClickListener(v -> finish());

        // Verify button
        btnVerify.setOnClickListener(v -> {
            String otp = etOTP.getText().toString().trim();
            if (validateOTP(otp)) {
                verifyOTP(otp);
            }
        });

        // Resend button
        btnResend.setOnClickListener(v -> {
            resendOTP();
            startResendTimer();
        });
    }

    private boolean validateOTP(String otp) {
        if (TextUtils.isEmpty(otp)) {
            etOTP.setError("OTP is required");
            etOTP.requestFocus();
            return false;
        }

        if (otp.length() != 6) {
            etOTP.setError("OTP must be 6 digits");
            etOTP.requestFocus();
            return false;
        }

        return true;
    }

    private void verifyOTP(String otp) {
        // Show progress
        showLoading(true);

        // TODO: Implement Firebase OTP verification
        // For now, simulate verification
        // In production, use credential.signInWithCredential()

        // Simulate network delay
        btnVerify.postDelayed(() -> {
            showLoading(false);

            // TODO: Check if user profile exists in Firebase
            // If yes, go to MainActivity
            // If no, go to ProfileSetupActivity

            Toast.makeText(this, "Verification successful!", Toast.LENGTH_SHORT).show();

            // Navigate to MainActivity (or ProfileSetup if new user)
            Intent intent = new Intent(OTPVerificationActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }, 1500);
    }

    private void resendOTP() {
        // TODO: Implement Firebase resend OTP
        Toast.makeText(this, "OTP resent to " + phoneNumber, Toast.LENGTH_SHORT).show();
    }

    private void startResendTimer() {
        btnResend.setEnabled(false);

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(RESEND_TIMEOUT, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = millisUntilFinished / 1000;
                tvResendTimer.setText(getString(R.string.resend_otp_in, (int) secondsRemaining));
            }

            @Override
            public void onFinish() {
                btnResend.setEnabled(true);
                tvResendTimer.setText("");
            }
        }.start();
    }

    private void showLoading(boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
            btnVerify.setEnabled(false);
            btnVerify.setText("");
        } else {
            progressBar.setVisibility(View.GONE);
            btnVerify.setEnabled(true);
            btnVerify.setText(R.string.verify);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}

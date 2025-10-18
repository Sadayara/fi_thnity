package tn.esprit.fi_thnity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import tn.esprit.fi_thnity.R;

public class PhoneAuthActivity extends AppCompatActivity {

    private TextInputEditText etCountryCode, etPhoneNumber;
    private MaterialButton btnSendOTP;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_auth);

        // Initialize views
        etCountryCode = findViewById(R.id.etCountryCode);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        btnSendOTP = findViewById(R.id.btnSendOTP);
        progressBar = findViewById(R.id.progressBar);

        // Send OTP button click
        btnSendOTP.setOnClickListener(v -> {
            String phoneNumber = etPhoneNumber.getText().toString().trim();

            if (validatePhoneNumber(phoneNumber)) {
                sendOTP(phoneNumber);
            }
        });
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            etPhoneNumber.setError("Phone number is required");
            etPhoneNumber.requestFocus();
            return false;
        }

        if (phoneNumber.length() != 8) {
            etPhoneNumber.setError("Phone number must be 8 digits");
            etPhoneNumber.requestFocus();
            return false;
        }

        return true;
    }

    private void sendOTP(String phoneNumber) {
        // Show progress
        showLoading(true);

        String fullPhoneNumber = "+216" + phoneNumber;

        // TODO: Implement Firebase Phone Authentication
        // For now, simulate sending OTP and navigate to verification screen
        // In production, use FirebaseAuth.getInstance().verifyPhoneNumber()

        // Simulate network delay
        btnSendOTP.postDelayed(() -> {
            showLoading(false);

            // Navigate to OTP Verification
            Intent intent = new Intent(PhoneAuthActivity.this, OTPVerificationActivity.class);
            intent.putExtra("phoneNumber", fullPhoneNumber);
            startActivity(intent);
        }, 1500);
    }

    private void showLoading(boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
            btnSendOTP.setEnabled(false);
            btnSendOTP.setText("");
        } else {
            progressBar.setVisibility(View.GONE);
            btnSendOTP.setEnabled(true);
            btnSendOTP.setText(R.string.send_otp);
        }
    }
}

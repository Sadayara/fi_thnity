package tn.esprit.fi_thnity.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import tn.esprit.fi_thnity.R;

public class ProfileSetupActivity extends AppCompatActivity {

    private ImageView ivProfilePhoto;
    private FloatingActionButton fabCamera;
    private TextInputEditText etName, etBio;
    private MaterialButton btnComplete;
    private ProgressBar progressBar;

    private Uri selectedImageUri;

    // Image picker launcher
    private final ActivityResultLauncher<Intent> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    selectedImageUri = result.getData().getData();
                    ivProfilePhoto.setImageURI(selectedImageUri);
                    ivProfilePhoto.setBackgroundColor(getResources().getColor(android.R.color.transparent, null));
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);

        // Initialize views
        ivProfilePhoto = findViewById(R.id.ivProfilePhoto);
        fabCamera = findViewById(R.id.fabCamera);
        etName = findViewById(R.id.etName);
        etBio = findViewById(R.id.etBio);
        btnComplete = findViewById(R.id.btnComplete);
        progressBar = findViewById(R.id.progressBar);

        // Camera/Gallery button click
        fabCamera.setOnClickListener(v -> openImagePicker());

        // Complete button click
        btnComplete.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String bio = etBio.getText().toString().trim();

            if (validateInput(name)) {
                completeSetup(name, bio);
            }
        });
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        imagePickerLauncher.launch(intent);
    }

    private boolean validateInput(String name) {
        if (TextUtils.isEmpty(name)) {
            etName.setError("Name is required");
            etName.requestFocus();
            return false;
        }

        if (name.length() < 2) {
            etName.setError("Name must be at least 2 characters");
            etName.requestFocus();
            return false;
        }

        return true;
    }

    private void completeSetup(String name, String bio) {
        // Show progress
        showLoading(true);

        // TODO: Upload profile photo to Firebase Storage
        // TODO: Save user data to Firebase Realtime Database
        // For now, simulate the process

        btnComplete.postDelayed(() -> {
            showLoading(false);

            Toast.makeText(this, "Profile created successfully!", Toast.LENGTH_SHORT).show();

            // Navigate to MainActivity
            Intent intent = new Intent(ProfileSetupActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }, 1500);
    }

    private void showLoading(boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
            btnComplete.setEnabled(false);
            btnComplete.setText("");
            fabCamera.setEnabled(false);
        } else {
            progressBar.setVisibility(View.GONE);
            btnComplete.setEnabled(true);
            btnComplete.setText(R.string.complete_setup);
            fabCamera.setEnabled(true);
        }
    }
}

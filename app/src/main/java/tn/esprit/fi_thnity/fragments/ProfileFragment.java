package tn.esprit.fi_thnity.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import tn.esprit.fi_thnity.R;
import tn.esprit.fi_thnity.activities.ProfileSetupActivity;

public class ProfileFragment extends Fragment {

    private ImageView ivProfilePhoto;
    private TextView tvName, tvPhone, tvTotalRides;
    private MaterialCardView cardEditProfile, cardMyRides, cardSettings;
    private MaterialButton btnLogout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        ivProfilePhoto = view.findViewById(R.id.ivProfilePhoto);
        tvName = view.findViewById(R.id.tvName);
        tvPhone = view.findViewById(R.id.tvPhone);
        tvTotalRides = view.findViewById(R.id.tvTotalRides);
        cardEditProfile = view.findViewById(R.id.cardEditProfile);
        cardMyRides = view.findViewById(R.id.cardMyRides);
        cardSettings = view.findViewById(R.id.cardSettings);
        btnLogout = view.findViewById(R.id.btnLogout);

        // TODO: Load user data from Firebase
        loadUserProfile();

        // Edit Profile
        cardEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), ProfileSetupActivity.class);
            startActivity(intent);
        });

        // My Rides
        cardMyRides.setOnClickListener(v -> {
            // TODO: Navigate to My Rides screen
            Toast.makeText(requireContext(), "My Rides", Toast.LENGTH_SHORT).show();
        });

        // Settings
        cardSettings.setOnClickListener(v -> {
            // TODO: Navigate to Settings screen
            Toast.makeText(requireContext(), "Settings", Toast.LENGTH_SHORT).show();
        });

        // Logout
        btnLogout.setOnClickListener(v -> {
            // TODO: Implement Firebase logout and navigate to Onboarding
            Toast.makeText(requireContext(), "Logout", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadUserProfile() {
        // TODO: Get user data from Firebase
        // For now, use placeholder data
        tvName.setText("John Doe");
        tvPhone.setText("+216 12 345 678");
        tvTotalRides.setText("0");
    }
}

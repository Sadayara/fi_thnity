package tn.esprit.fi_thnity.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;

import tn.esprit.fi_thnity.BuildConfig;
import tn.esprit.fi_thnity.R;
import tn.esprit.fi_thnity.activities.BroadcastRideActivity;
import tn.esprit.fi_thnity.models.Ride;

public class HomeFragment extends Fragment {

    private MapView mapView;
    private TextView tvWelcome, tvLocation, tvActiveRides;
    private MaterialCardView cardNeedRide, cardOfferRide;
    private FloatingActionButton fabCurrentLocation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        mapView = view.findViewById(R.id.mapView);
        tvWelcome = view.findViewById(R.id.tvWelcome);
        tvLocation = view.findViewById(R.id.tvLocation);
        tvActiveRides = view.findViewById(R.id.tvActiveRides);
        cardNeedRide = view.findViewById(R.id.cardNeedRide);
        cardOfferRide = view.findViewById(R.id.cardOfferRide);
        fabCurrentLocation = view.findViewById(R.id.fabCurrentLocation);

        // Setup map
        setupMap(savedInstanceState);

        // Setup welcome message
        // TODO: Get user name from Firebase
        tvWelcome.setText(getString(R.string.welcome_back, "User"));

        // Setup quick actions
        cardNeedRide.setOnClickListener(v -> {
            // Navigate to Broadcast Ride for Ride Request
            Intent intent = new Intent(requireContext(), BroadcastRideActivity.class);
            intent.putExtra(BroadcastRideActivity.EXTRA_RIDE_TYPE, Ride.RideType.REQUEST.name());
            startActivity(intent);
        });

        cardOfferRide.setOnClickListener(v -> {
            // Navigate to Broadcast Ride for Ride Offer
            Intent intent = new Intent(requireContext(), BroadcastRideActivity.class);
            intent.putExtra(BroadcastRideActivity.EXTRA_RIDE_TYPE, Ride.RideType.OFFER.name());
            startActivity(intent);
        });

        // Setup current location button
        fabCurrentLocation.setOnClickListener(v -> {
            // TODO: Get current location and center map
            Toast.makeText(requireContext(), "Centering on your location", Toast.LENGTH_SHORT).show();
        });
    }

    private void setupMap(Bundle savedInstanceState) {
        String apiKey = BuildConfig.MAPTILER_API_KEY;
        String mapId = "streets-v2";
        String styleUrl = "https://api.maptiler.com/maps/" + mapId + "/style.json?key=" + apiKey;

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(map -> {
            map.setStyle(styleUrl, style -> {
                // Set initial camera position (Tunis)
                map.setCameraPosition(
                        new CameraPosition.Builder()
                                .target(new LatLng(36.8065, 10.1815))
                                .zoom(12.0)
                                .build()
                );
            });
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mapView != null) mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mapView != null) mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mapView != null) mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mapView != null) mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mapView != null) mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mapView != null) mapView.onLowMemory();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mapView != null) mapView.onDestroy();
    }
}

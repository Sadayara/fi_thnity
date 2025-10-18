package tn.esprit.fi_thnity.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import tn.esprit.fi_thnity.BuildConfig;
import tn.esprit.fi_thnity.R;

public class LocationSelectionActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
    public static final String EXTRA_LOCATION_TYPE = "location_type";
    public static final String EXTRA_LATITUDE = "latitude";
    public static final String EXTRA_LONGITUDE = "longitude";
    public static final String EXTRA_ADDRESS = "address";

    private MapView mapView;
    private TextView tvSelectedAddress, tvCoordinates, tvTitle;
    private TextInputEditText etSearch;
    private MaterialButton btnConfirm;
    private FloatingActionButton fabCurrentLocation;
    private ImageButton btnBack;

    private LatLng selectedLocation;
    private String selectedAddress = "";
    private String locationType = ""; // "origin" or "destination"
    private Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_selection);

        // Get location type from intent
        locationType = getIntent().getStringExtra(EXTRA_LOCATION_TYPE);
        if (locationType == null) locationType = "origin";

        // Initialize geocoder
        geocoder = new Geocoder(this, Locale.getDefault());

        // Initialize views
        mapView = findViewById(R.id.mapView);
        tvSelectedAddress = findViewById(R.id.tvSelectedAddress);
        tvCoordinates = findViewById(R.id.tvCoordinates);
        tvTitle = findViewById(R.id.tvTitle);
        etSearch = findViewById(R.id.etSearch);
        btnConfirm = findViewById(R.id.btnConfirm);
        fabCurrentLocation = findViewById(R.id.fabCurrentLocation);
        btnBack = findViewById(R.id.btnBack);

        // Set title based on location type
        tvTitle.setText(locationType.equals("origin") ? "Select Pickup Location" : "Select Dropoff Location");

        // Setup map
        setupMap(savedInstanceState);

        // Back button
        btnBack.setOnClickListener(v -> finish());

        // Confirm button
        btnConfirm.setOnClickListener(v -> confirmLocation());

        // Current location button
        fabCurrentLocation.setOnClickListener(v -> requestCurrentLocation());

        // Search (placeholder - will integrate with geocoding API later)
        etSearch.setOnClickListener(v ->
            showCustomAlert("Search", "Address search will be available soon!", "OK", null)
        );
    }

    private void setupMap(Bundle savedInstanceState) {
        String apiKey = BuildConfig.MAPTILER_API_KEY;
        String mapId = "streets-v2";
        String styleUrl = "https://api.maptiler.com/maps/" + mapId + "/style.json?key=" + apiKey;

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(map -> {
            map.setStyle(styleUrl, style -> {
                // Set initial camera position (Tunis)
                LatLng initialPosition = new LatLng(36.8065, 10.1815);
                map.setCameraPosition(
                        new CameraPosition.Builder()
                                .target(initialPosition)
                                .zoom(14.0)
                                .build()
                );

                // Listen for camera movements
                map.addOnCameraIdleListener(() -> {
                    LatLng center = map.getCameraPosition().target;
                    selectedLocation = center;
                    updateLocationInfo(center);
                });
            });
        });
    }

    private void updateLocationInfo(LatLng location) {
        // Update coordinates
        tvCoordinates.setText(String.format(Locale.getDefault(),
            "%.6f, %.6f", location.getLatitude(), location.getLongitude()));

        // Get address using geocoder
        tvSelectedAddress.setText(R.string.loading_address);
        new Thread(() -> {
            try {
                List<Address> addresses = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    1
                );

                if (addresses != null && !addresses.isEmpty()) {
                    Address address = addresses.get(0);
                    StringBuilder fullAddress = new StringBuilder();

                    // Build readable address
                    if (address.getFeatureName() != null) {
                        fullAddress.append(address.getFeatureName()).append(", ");
                    }
                    if (address.getThoroughfare() != null) {
                        fullAddress.append(address.getThoroughfare()).append(", ");
                    }
                    if (address.getLocality() != null) {
                        fullAddress.append(address.getLocality());
                    }

                    selectedAddress = fullAddress.toString();
                    if (selectedAddress.endsWith(", ")) {
                        selectedAddress = selectedAddress.substring(0, selectedAddress.length() - 2);
                    }

                    runOnUiThread(() -> tvSelectedAddress.setText(selectedAddress));
                } else {
                    selectedAddress = "Unknown location";
                    runOnUiThread(() -> tvSelectedAddress.setText(selectedAddress));
                }
            } catch (IOException e) {
                e.printStackTrace();
                selectedAddress = String.format(Locale.getDefault(),
                    "%.6f, %.6f", location.getLatitude(), location.getLongitude());
                runOnUiThread(() -> tvSelectedAddress.setText(selectedAddress));
            }
        }).start();
    }

    private void requestCurrentLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Request permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Permission already granted
            showCustomAlert("Location", "Getting your current location...", "OK", null);
            // TODO: Implement actual location retrieval
        }
    }

    private void confirmLocation() {
        if (selectedLocation == null) {
            showCustomAlert("Error", "Please select a location", "OK", null);
            return;
        }

        // Return result
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_LATITUDE, selectedLocation.getLatitude());
        resultIntent.putExtra(EXTRA_LONGITUDE, selectedLocation.getLongitude());
        resultIntent.putExtra(EXTRA_ADDRESS, selectedAddress);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    // Custom Alert Dialog (no Android logo)
    private void showCustomAlert(String title, String message, String positiveText, Runnable positiveAction) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomAlertDialog);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveText, (dialog, which) -> {
            if (positiveAction != null) positiveAction.run();
            dialog.dismiss();
        });
        builder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestCurrentLocation();
            } else {
                showCustomAlert("Permission Denied",
                    "Location permission is required to get your current location",
                    "OK", null);
            }
        }
    }

    // MapView lifecycle methods
    @Override
    protected void onStart() {
        super.onStart();
        if (mapView != null) mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mapView != null) mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mapView != null) mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mapView != null) mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mapView != null) mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mapView != null) mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mapView != null) mapView.onDestroy();
    }
}

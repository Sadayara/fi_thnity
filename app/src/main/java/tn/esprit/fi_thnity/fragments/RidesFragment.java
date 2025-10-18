package tn.esprit.fi_thnity.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import tn.esprit.fi_thnity.R;

public class RidesFragment extends Fragment {

    private RecyclerView recyclerRides;
    private View layoutEmpty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rides, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerRides = view.findViewById(R.id.recyclerRides);
        layoutEmpty = view.findViewById(R.id.layoutEmpty);

        // Setup RecyclerView
        recyclerRides.setLayoutManager(new LinearLayoutManager(requireContext()));

        // TODO: Setup adapter and load rides from Firebase
        // For now, show empty state
        showEmptyState(true);
    }

    private void showEmptyState(boolean show) {
        if (show) {
            recyclerRides.setVisibility(View.GONE);
            layoutEmpty.setVisibility(View.VISIBLE);
        } else {
            recyclerRides.setVisibility(View.VISIBLE);
            layoutEmpty.setVisibility(View.GONE);
        }
    }
}

package tn.esprit.fi_thnity.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import tn.esprit.fi_thnity.R;

public class CommunityFragment extends Fragment {

    private RecyclerView recyclerCommunity;
    private FloatingActionButton fabNewPost;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_community, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerCommunity = view.findViewById(R.id.recyclerCommunity);
        fabNewPost = view.findViewById(R.id.fabNewPost);

        // Setup RecyclerView
        recyclerCommunity.setLayoutManager(new LinearLayoutManager(requireContext()));

        // TODO: Setup adapter and load community posts from Firebase

        // New post button
        fabNewPost.setOnClickListener(v -> {
            // TODO: Open new post dialog/activity
            Toast.makeText(requireContext(), "Create new community post", Toast.LENGTH_SHORT).show();
        });
    }
}

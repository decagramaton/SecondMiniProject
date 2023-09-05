package com.example.secondminiproject.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentReviewUpdateBinding;


public class ReviewUpdateFragment extends Fragment {
    private static final String TAG = "ReviewUpdateFragment";
    private FragmentReviewUpdateBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);

        binding = FragmentReviewUpdateBinding.inflate(inflater);

        initBtnReviewList();
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private void initBtnReviewList() {
        binding.btnReviewUpdateReviewList.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }
}
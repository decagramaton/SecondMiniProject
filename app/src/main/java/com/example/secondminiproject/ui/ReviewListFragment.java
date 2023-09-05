package com.example.secondminiproject.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentReviewListBinding;


public class ReviewListFragment extends Fragment {

    private static final String TAG = "ReviewListFragment";
    private FragmentReviewListBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);

        binding = FragmentReviewListBinding.inflate(inflater);

        initBtnMyPage();
        initBtnReviewWrite();
        initBtnReviewUpdate();
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private void initBtnMyPage() {
        binding.btnReviewListMyPage.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }

    private void initBtnReviewWrite() {
        binding.btnReviewListReviewWrite.setOnClickListener(v -> {
            navController.navigate(R.id.action_dest_review_list_to_dest_review_write);
        });
    }

    private void initBtnReviewUpdate() {
        binding.btnReviewListReviewUpdate.setOnClickListener(v -> {
            navController.navigate(R.id.action_dest_review_list_to_dest_review_update);
        });
    }
}
package com.example.secondminiproject.ui.review;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentReviewWriteBinding;


public class ReviewWriteFragment extends Fragment {
    private static final String TAG = "ReviewWriteFragment";
    private FragmentReviewWriteBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);

        binding = FragmentReviewWriteBinding.inflate(inflater);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}
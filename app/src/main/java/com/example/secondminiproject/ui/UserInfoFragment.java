package com.example.secondminiproject.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentUserInfoBinding;


public class UserInfoFragment extends Fragment {
    private static final String TAG = "UserInfoFragment";
    private FragmentUserInfoBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController= NavHostFragment.findNavController(this);

        binding = FragmentUserInfoBinding.inflate(inflater);

        initBtnMyPage();
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private void initBtnMyPage() {
        binding.btnUserInfoMyPage.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }
}
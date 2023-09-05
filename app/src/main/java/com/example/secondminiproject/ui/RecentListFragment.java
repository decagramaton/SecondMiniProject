package com.example.secondminiproject.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentRecentListBinding;
import com.example.secondminiproject.databinding.FragmentReservationDetailBinding;

public class RecentListFragment extends Fragment {

    private static final String TAG = "RecentListFragment";
    private FragmentRecentListBinding binding;

    private NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRecentListBinding.inflate(inflater);

        navController = NavHostFragment.findNavController(this);

        initBtnRecentListMyPage();
        initBtnRecentListProductDetail();
        return binding.getRoot();
    }

    private void initBtnRecentListProductDetail() {
        binding.btnRecentListProductDetail.setOnClickListener(v -> {
            navController.navigate(R.id.action_dest_recent_list_to_dest_product_detail);
        });
    }

    private void initBtnRecentListMyPage() {
        binding.btnRecentListMyPage.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }
}
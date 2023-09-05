package com.example.secondminiproject.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentMyPageBinding;


public class MyPageFragment extends Fragment {

    private static final String TAG = "MyPageFragment";
    private FragmentMyPageBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);

        binding =FragmentMyPageBinding.inflate(inflater);

        initBtnReviewList();
        initBtnReservationList();
        initBtnWishList();
        initBtnUserInfo();
        initBtnRecentList();
        initBtnHome();



        return binding.getRoot();
    }

    private void initBtnHome() {
        binding.btnMyPageHome.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }

    private void initBtnReviewList() {
        binding.btnMyPageReviewList.setOnClickListener(v -> {
            navController.navigate(R.id.action_dest_my_page_to_dest_review_list);
        });
    }

    private void initBtnReservationList() {
        binding.btnMyPageReservationList.setOnClickListener(v -> {
            navController.navigate(R.id.action_dest_my_page_to_dest_reservation_list);
        });
    }

    private void initBtnWishList() {
        binding.btnMyPageWishList.setOnClickListener(v -> {
            navController.navigate(R.id.action_dest_my_page_to_dest_wish_list);
        });
    }

    private void initBtnUserInfo() {
        binding.btnMyPageUserInfo.setOnClickListener(v -> {
            navController.navigate(R.id.action_dest_my_page_to_dest_user_info);
        });
    }

    private void initBtnRecentList() {
        binding.btnMyPageRecentList.setOnClickListener(v -> {
            navController.navigate(R.id.action_dest_my_page_to_dest_recent_list);
        });
    }
}
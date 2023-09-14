package com.example.secondminiproject.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentMyPageBinding;
import com.example.secondminiproject.datastore.AppKeyValueStore;


public class MyPageFragment extends Fragment {

    private static final String TAG = "MyPageFragment";
    private FragmentMyPageBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);

        //binding =FragmentMyPageBinding.inflate(inflater);
        binding = FragmentMyPageBinding.inflate(getLayoutInflater());

        navController = NavHostFragment.findNavController(this);

        initBtnReviewList();
        initBtnReservationList();
        initBtnWishList();
        initBtnUserInfo();
        initBtnRecentList();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume 실행");
        settingLoginLogout();
    }

    private void settingLoginLogout() {

        String mid = AppKeyValueStore.getValue(getContext(), "mid");
        Log.i(TAG, "settingLoginLogout: " + mid);
        if(mid == null){
            binding.btnMyPageLoginLogout.setText("로그인");

            binding.btnMyPageLoginLogout.setOnClickListener(v -> {
                navController.navigate(R.id.dest_login);
            });
        } else {
            binding.btnMyPageLoginLogout.setText("로그아웃");
            AppKeyValueStore.remove(getContext(), "mid");
            AppKeyValueStore.remove(getContext(), "mpassword");

            binding.btnMyPageLoginLogout.setOnClickListener(v -> {
                navController.popBackStack(R.id.dest_home, false);
            });
        }
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
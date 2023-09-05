package com.example.secondminiproject.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private FragmentHomeBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);

        initBtnProductList();
        initBtnLogin();
        initBtnMyPage();

        return binding.getRoot();
    }

    private void initBtnProductList() {
        binding.btnHomeProductList.setOnClickListener(v->{
            navController.navigate(R.id.action_dest_home_to_dest_product_list);
        });
    }

    private void initBtnLogin() {
        binding.btnHomeLogin.setOnClickListener(v->{
            navController.navigate(R.id.action_dest_home_to_dest_login);
        });
    }

    private void initBtnMyPage() {
        binding.btnHomeMypage.setOnClickListener(v->{
            navController.navigate(R.id.action_dest_home_to_dest_my_page);
        });
    }
}
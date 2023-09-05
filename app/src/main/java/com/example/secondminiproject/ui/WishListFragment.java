package com.example.secondminiproject.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentWishListBinding;

public class WishListFragment extends Fragment {

    private static final String TAG = "WishListFragment";
    private FragmentWishListBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentWishListBinding.inflate(inflater);
        navController = NavHostFragment.findNavController(this);

        initBtnWishListMyPage();
        initBtnWishListProductDetail();

        return binding.getRoot();
    }

    private void initBtnWishListMyPage() {
        binding.btnWishListMyPage.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }

    private void initBtnWishListProductDetail() {
        binding.btnWishListProductDetail.setOnClickListener(v->{
            navController.navigate(R.id.action_dest_wish_list_to_dest_product_detail);
        });
    }
}
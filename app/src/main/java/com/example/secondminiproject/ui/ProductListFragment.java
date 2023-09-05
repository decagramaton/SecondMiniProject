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
import com.example.secondminiproject.databinding.FragmentProductListBinding;

public class ProductListFragment extends Fragment {

    private static final String TAG = "ProductListFragment";
    private FragmentProductListBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProductListBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);

        initBtnProductDetail();
        initBtnHome();

        return binding.getRoot();
    }

    private void initBtnProductDetail() {
        binding.btnProductListProductDetail.setOnClickListener(v->{
            navController.navigate(R.id.action_dest_product_list_to_dest_product_detail);
        });
    }

    private void initBtnHome() {
        binding.btnProductListHome.setOnClickListener(v->{
            navController.popBackStack();
        });
    }
}
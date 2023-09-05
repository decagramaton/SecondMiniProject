package com.example.secondminiproject.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentPaymentBinding;
import com.example.secondminiproject.databinding.FragmentProductDetailBinding;


public class PaymentFragment extends Fragment {

    private static final String TAG = "PaymentFragment";
    private FragmentPaymentBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPaymentBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);

        initBtnProductDetail();

        return binding.getRoot();
    }

    private void initBtnProductDetail() {
        binding.btnPaymentProductDetail.setOnClickListener(v->{
            navController.popBackStack();
        });
    }
}
package com.example.secondminiproject.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentProductDetailMainBinding;

public class ProductDetailMainFragment extends Fragment {

    private FragmentProductDetailMainBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProductDetailMainBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }
}
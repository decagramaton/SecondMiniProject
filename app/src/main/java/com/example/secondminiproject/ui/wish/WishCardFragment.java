package com.example.secondminiproject.ui.wish;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentWishCardBinding;


public class WishCardFragment extends Fragment {
    private FragmentWishCardBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWishCardBinding.inflate(getLayoutInflater());




        return inflater.inflate(R.layout.fragment_wish_card, container, false);
    }

}
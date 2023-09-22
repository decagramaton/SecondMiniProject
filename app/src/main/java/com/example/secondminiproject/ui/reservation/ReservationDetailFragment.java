package com.example.secondminiproject.ui.reservation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentReservationDetailBinding;
import com.example.secondminiproject.databinding.FragmentReservationListBinding;

public class ReservationDetailFragment extends Fragment {

    private static final String TAG = "ReservationDetailFragment";
    private FragmentReservationDetailBinding binding;

    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentReservationDetailBinding.inflate(inflater);
        navController = NavHostFragment.findNavController(this);

        initBtnReservationDetailReservationList();
        return binding.getRoot();
    }

    private void initBtnReservationDetailReservationList() {
        binding.btnReservationDetailReservationList.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }
}
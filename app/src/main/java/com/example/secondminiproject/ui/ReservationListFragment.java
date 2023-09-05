package com.example.secondminiproject.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentReservationListBinding;
import com.example.secondminiproject.databinding.FragmentWishListBinding;

public class ReservationListFragment extends Fragment {

    private static final String TAG = "ReservationListFragment";
    private FragmentReservationListBinding binding;

    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentReservationListBinding.inflate(inflater);

        navController = NavHostFragment.findNavController(this);

        initBtnReservationListMyPage();
        initBtnReservationListReservationDetail();

        return binding.getRoot();
    }

    private void initBtnReservationListReservationDetail() {
        binding.btnReservationListReservationDetail.setOnClickListener(v -> {
            navController.navigate(R.id.action_dest_reservation_list_to_reservationDetailFragment);
        });
    }

    private void initBtnReservationListMyPage() {
        binding.btnReservationListMyPage.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }
}
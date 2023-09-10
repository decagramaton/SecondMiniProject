package com.example.secondminiproject.ui.reservation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentReservationListCardBinding;

public class ReservationListCardFragment extends Fragment {
    private static final String TAG = "ReservationListCardFrag";
    private FragmentReservationListCardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReservationListCardBinding.inflate(inflater);

        return binding.getRoot();
    }
}
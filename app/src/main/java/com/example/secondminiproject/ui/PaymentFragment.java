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

        initReservationInfo();
      /*  initBtnProductDetail();*/
        initBtnPayFinish();

        return binding.getRoot();
    }

    private void initReservationInfo() {
        Bundle bundle = getArguments();
        binding.reservationAdultPrice.setText(String.valueOf(bundle.getInt("eachAdultPrice")));
        binding.reservationChildPrice.setText(String.valueOf(bundle.getInt("eachChildPrice")));
        binding.reservationAdultNumber.setText(String.valueOf(bundle.getInt("adultNumber")));
        binding.reservationChildNumber.setText(String.valueOf(bundle.getInt("childNumber")));
        binding.reservationTotalPrice.setText(String.valueOf(bundle.getInt("totalPrice")));
    }

    private void initBtnPayFinish() {
        binding.btnPaymentMakeReservation.setOnClickListener(v -> {
            navController.navigate(R.id.dest_reservation_list);
        });
    }

    /*private void initBtnProductDetail() {
        binding.btnPaymentProductDetail.setOnClickListener(v->{
            navController.popBackStack();
        });
    }*/
}
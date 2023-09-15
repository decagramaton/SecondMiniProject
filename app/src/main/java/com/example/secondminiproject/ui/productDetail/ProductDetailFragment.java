package com.example.secondminiproject.ui.productDetail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentProductDetailBinding;
import com.example.secondminiproject.databinding.FragmentProductListBinding;
import com.example.secondminiproject.ui.home.HomeBannerAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Timer;
import java.util.TimerTask;


public class ProductDetailFragment extends Fragment {

    private static final String TAG = "ProductDetailFragment";
    private FragmentProductDetailBinding binding;
    private NavController navController;
    private BottomSheetDialogFragment bottomSheet;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProductDetailBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);

        initBtnMakeReservation();
        initPagerView();

        return binding.getRoot();
    }

    private void initBtnMakeReservation() {

        binding.btnProductDetailPayment.setOnClickListener(v -> {
            //navController.navigate(R.id.dest_payment);
           ProductDetailBottomSheetFragment productDetailBottomSheetFragment = new ProductDetailBottomSheetFragment();
           productDetailBottomSheetFragment.show(getActivity().getSupportFragmentManager(), "productDetailBottomSheetFragment");
        });
    }


    private void initPagerView() {
        DetailBannerAdapter detailBannerAdapter = new DetailBannerAdapter(getActivity());
        binding.detailBanner.setAdapter(detailBannerAdapter);
    }

}
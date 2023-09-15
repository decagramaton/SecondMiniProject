package com.example.secondminiproject.ui.productDetail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentProductDetailBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

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
        //initVideo();
        initPagerView();

        hideBottomNavigation(true);

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hideBottomNavigation(false);
    }

    public void hideBottomNavigation(Boolean bool) {
        BottomNavigationView bottomNavigation = getActivity().findViewById(R.id.bottom_navigation);
        if (bool == true)
            bottomNavigation.setVisibility(View.GONE);
        else
            bottomNavigation.setVisibility(View.VISIBLE);
    }

}
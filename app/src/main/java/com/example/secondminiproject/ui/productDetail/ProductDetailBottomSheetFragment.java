package com.example.secondminiproject.ui.productDetail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentProductDetailBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class ProductDetailBottomSheetFragment extends BottomSheetDialogFragment {

    //초기 변수 설정
    private View view;
    //인터페이스 변수
    private FragmentProductDetailBottomSheetBinding binding;
    private NavController navController;

    private Button btn_hide_bt_sheet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentProductDetailBottomSheetBinding.inflate(inflater);
        view = binding.getRoot();

        navController = NavHostFragment.findNavController(this);

        initBtnMakeReservation();


        // Inflate the layout for this fragment
        return view;
    }

    private void initBtnMakeReservation() {
        binding.btnProductDetailBottomSheetMakeReservation.setOnClickListener(v -> {
            dismiss();
            navController.navigate(R.id.dest_payment);
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        view.findViewById(R.id.btn_product_detail_bottom_sheet_close).setOnClickListener(v -> {
            dismiss();
        });
    }
}
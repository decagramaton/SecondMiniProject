package com.example.secondminiproject.ui.productDetail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

    private Button btn_hide_bt_sheet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentProductDetailBottomSheetBinding.inflate(inflater);
        view = binding.getRoot();


        // Inflate the layout for this fragment
        return view;
    }
}
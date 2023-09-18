package com.example.secondminiproject.ui.productDetail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentProductDetailBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.DecimalFormat;


public class ProductDetailBottomSheetFragment extends BottomSheetDialogFragment {

    private static final String TAG = "ProductDetailBottomSheet";
    //초기 변수 설정
    private View view;
    //인터페이스 변수
    private FragmentProductDetailBottomSheetBinding binding;
    private NavController navController;
    private Button btn_hide_bt_sheet;
    private TextView adultNum;
    private int changingAdultNum =0;
    private TextView adultPrice;
    private TextView childNum;
    private int changingChildNum =0;
    private TextView childPrice;
    private TextView totalPrice;
    int calAdultPrice = 0;
    int calChildPrice = 0;
    int calTotalPrice = 0;
    int eachAdultPrice=0;
    int eachChildPrice=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentProductDetailBottomSheetBinding.inflate(inflater);
        view = binding.getRoot();

        navController = NavHostFragment.findNavController(this);

        adultNum = binding.productDetailBottomSheetAdultNumber;
        changingAdultNum = Integer.parseInt(adultNum.getText().toString());
        adultPrice = binding.productDetailBottomSheetAdultPrice;
        childNum = binding.productDetailBottomSheetChildNumber;
        changingChildNum = Integer.parseInt(childNum.getText().toString());
        childPrice =binding.productDetailBottomSheetChildPrice;
        totalPrice=binding.productDetailBottomSheetTotalPrice;


        initBtnMakeReservation();

        initBtnChangeQuantity();


        // Inflate the layout for this fragment
        return view;
    }

    private void initBtnChangeQuantity() {
        eachAdultPrice = Integer.parseInt(adultPrice.getText().toString());
        eachChildPrice = Integer.parseInt(childPrice.getText().toString());

        binding.btnProductDetailBottomSheetAdultMinus.setOnClickListener(v -> {

            if(changingAdultNum > 0){
                changingAdultNum--;
                adultNum.setText(String.valueOf(changingAdultNum));
                calAdultPrice = eachAdultPrice*changingAdultNum;
            }
            initBtnChangeQuantity();
        });

        binding.btnProductDetailBottomSheetAdultPlus.setOnClickListener(v -> {

            changingAdultNum++;
            adultNum.setText(String.valueOf(changingAdultNum));
            calAdultPrice = eachAdultPrice*changingAdultNum;
            initBtnChangeQuantity();
        });

        binding.btnProductDetailBottomSheetChildMinus.setOnClickListener(v -> {

            if(changingChildNum > 0){
                changingChildNum--;
                childNum.setText(String.valueOf(changingChildNum));
                calChildPrice = eachChildPrice*changingChildNum;
            }
            initBtnChangeQuantity();
        });

        binding.btnProductDetailBottomSheetChildPlus.setOnClickListener(v -> {

            changingChildNum++;
            childNum.setText(String.valueOf(changingChildNum));
            calChildPrice = eachChildPrice*changingChildNum;
            initBtnChangeQuantity();
        });
        calculateTotalPrice();

    }

    private void calculateTotalPrice() {
        calTotalPrice = calAdultPrice+calChildPrice;
        totalPrice.setText(String.valueOf(calTotalPrice));
    }

    private void initBtnMakeReservation() {
        binding.btnProductDetailBottomSheetMakeReservation.setOnClickListener(v -> {
            dismiss();
            Bundle bundle = new Bundle();
            bundle.putInt("eachAdultPrice",eachAdultPrice);
            bundle.putInt("eachChildPrice",eachChildPrice);
            bundle.putInt("adultNumber",changingAdultNum);
            bundle.putInt("childNumber",changingChildNum);
            bundle.putInt("totalPrice",calTotalPrice);
            navController.navigate(R.id.dest_payment,bundle);
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

    }
}
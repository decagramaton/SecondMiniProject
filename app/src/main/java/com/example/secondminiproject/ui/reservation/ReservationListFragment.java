package com.example.secondminiproject.ui.reservation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentReservationListBinding;
import com.example.secondminiproject.databinding.FragmentReservationListDateBinding;
import com.example.secondminiproject.dto.Reservation;

import java.util.Random;

public class ReservationListFragment extends Fragment {

    private static final String TAG = "ReservationListFragment";
    private FragmentReservationListBinding binding;
    private FragmentReservationListDateBinding dateBinding;

    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentReservationListBinding.inflate(inflater);
        navController = NavHostFragment.findNavController(this);
        initRecyclerView();


        return binding.getRoot();
    }
    private void initRecyclerView() {
        //수직방향으로 1라인에 1개의 viewHolder가 들어가는 레이아웃 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false
        );
        //2d열로 출력할때
        /* GridLayoutManager linearLayoutManager = new GridLayoutManager(this,2);*/
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        //어뎁터 생성
        ReservationAdapter reservationAdapter = new ReservationAdapter();

        //데이터 받아와서 어뎁터에 설정
        Random random = new Random();
        for(int i=1; i <=5; i++){
            Reservation reservation = new Reservation();
            reservation.setReservationNo(i);
            reservation.setProductName(i+ "번째 상품의 상품명이 들어간다.");
            //이미지명, resource의 어디에있는지, 패키지명("com.example.myapplication"  or getApplication().getPackageName() ) 을 넣어야함.
            reservation.setReservationImage(getResources().getIdentifier("photo" +(random.nextInt(17)+1), "drawable","com.example.secondminiproject"));
            reservation.setImsiReservationDate("23.09.08");
            reservation.setStartDate("23.09.23");
            reservation.setReservationState(1);
            /*reservation.setReservationAdultNumber(random.nextInt(3)+1);
            reservation.setReservationChildNumber(random.nextInt(2)+1);*/

            //productAdapter.addProduct(product);
            reservationAdapter.addReservation(reservation);
        }

        //리사이클러뷰에 어댑터 설정
        binding.recyclerView.setAdapter(reservationAdapter);

    }

}
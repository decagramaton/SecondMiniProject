package com.example.secondminiproject.ui.reservation;

import android.app.Service;
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
import com.example.secondminiproject.datastore.AppKeyValueStore;
import com.example.secondminiproject.dto.Reservation;
import com.example.secondminiproject.service.ProductService;
import com.example.secondminiproject.service.ReservationService;
import com.example.secondminiproject.service.ServiceProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationListFragment extends Fragment {

    private static final String TAG = "ReservationListFragment";
    private FragmentReservationListBinding binding;
    private FragmentReservationListDateBinding dateBinding;
    private ReservationDetailAdapter.OnItemClickListener onItemClickListener;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentReservationListBinding.inflate(inflater);
        navController = NavHostFragment.findNavController(this);
        initRecyclerView();



        return binding.getRoot();
    }



    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerViewReservationList.setLayoutManager(linearLayoutManager);

        //어뎁터 생성
        ReservationAdapter reservationAdapter = new ReservationAdapter();

        //회원 번호 받아오기
        int userNo = Integer.parseInt(AppKeyValueStore.getValue(getContext(),"userNo"));
        Log.i(TAG, "프레그먼트의 회원번호 : "+userNo);

        //
        ProductService productService = ServiceProvider.getProductService(getContext());
        ReservationService reservationService = ServiceProvider.getReservationService(getContext());
        Call<List<Long>> call = reservationService.getReservationDayList(userNo);
        call.enqueue(new Callback<List<Long>>() {
            @Override
            public void onResponse(Call<List<Long>> call, Response<List<Long>> response) {
                //날짜 리스트 받아오기
                List<Long> reservationDays = response.body();
                Log.i(TAG, "콜백함수의 reservationDays : "+reservationDays);

                //어뎁터에 설정
                reservationAdapter.setReservationDayListAndServiceAndUserNo(reservationDays,reservationService,productService,userNo);
                reservationAdapter.setNavController(navController);

                //리사이클러뷰에 어댑터 설정
                binding.recyclerViewReservationList.setAdapter(reservationAdapter);

                binding.recyclerViewReservationList.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {

                    if((!v.canScrollVertically(-1))){
                        binding.btnReservationListGoListTop.hide();
                    }else {
                        binding.btnReservationListGoListTop.show();
                    }

                });

                binding.btnReservationListGoListTop.setOnClickListener(v -> {
                    binding.recyclerViewReservationList.scrollToPosition(0);
                });
            }

            @Override
            public void onFailure(Call<List<Long>> call, Throwable t) {

            }
        });


    }

    public void setOnItemClickListener(ReservationDetailAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ReservationDetailAdapter.OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }
}
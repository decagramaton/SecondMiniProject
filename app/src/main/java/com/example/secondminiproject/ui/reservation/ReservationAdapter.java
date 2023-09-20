package com.example.secondminiproject.ui.reservation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.datastore.AppKeyValueStore;
import com.example.secondminiproject.dto.Reservation;
import com.example.secondminiproject.service.ProductService;
import com.example.secondminiproject.service.ReservationService;
import com.example.secondminiproject.service.ServiceProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationViewHolder> {
    private static final String TAG = "ReservationAdapter";
    private List<Long> reservationDayList = new ArrayList<>();
    private List<String> dateList = new ArrayList<>();
    private NavController navController;
    private ReservationService reservationService;
    private ProductService productService;
    private int userNo;
    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View dateView = layoutInflater.inflate(R.layout.fragment_reservation_list_date, parent, false);
        ReservationViewHolder reservationViewHolder = new ReservationViewHolder(dateView);

        return reservationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder holder, int position) {
        // Step1. 1차 배열 데이터 설정 - 날짜 별 목록
        Long reservationDay = reservationDayList.get(position);
        holder.setData(reservationDay);
        
        //2차 배열에 세팅할 ReservationList 받아오기
        //long타입을 Date 타입으로 넣어서 서버로 보낸다
        Date date = new Date(reservationDay);

        Call<List<Reservation>> call = reservationService.getReservationListByDay(date,userNo);
        Log.i(TAG, "여기까지는 굳?");
        call.enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {
                List<Reservation> reservationList = response.body();
                Log.i(TAG, "날짜별 예약 내역 잘들어오나? :  "+reservationList);

                // Step2. 2차 배열 전용 초기 레이아웃 설정 - 예약 내역 목록
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.VERTICAL, false);

                // Step3. 2차 배열 전용 리싸이클 뷰 어댑터 설정 - 예약 내역 목록
                ReservationDetailAdapter reservationDetailAdapter = new ReservationDetailAdapter(reservationList, navController,productService);
                holder.recyclerViewReservationListDate.setLayoutManager(linearLayoutManager);
                holder.recyclerViewReservationListDate.setAdapter(reservationDetailAdapter);

                reservationDetailAdapter.setOnItemClickListener(new ReservationDetailAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int position) {
                        Log.i(TAG, position + "빔 항목 클릭 됨 ");
                        //해당 포지션의 아이템을 boardAdapter을 통해 받아온다.
                        Reservation reservation = reservationDetailAdapter.getItem(position);
                        Bundle args = new Bundle();
                        //Board 객체를 전달해야하기때문에 (Board 객체에는 Serializable 이 임플먼트 되잇어야한다)
                        args.putSerializable("reservation", reservation);
                        navController.navigate(R.id.action_dest_reservation_list_to_reservationDetailFragment,args);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {

            }
        });

    }

    public void setReservationDayListAndServiceAndUserNo(List<Long> reservationDayList, ReservationService reservationService, ProductService productService, int userNo) {
        this.reservationDayList = reservationDayList;
        this.reservationService = reservationService;
        this.productService = productService;
        this.userNo = userNo;
    }

    @Override
    public int getItemCount() {
        return reservationDayList.size();
    }

    public void setNavController(NavController navController){
        this.navController=navController;
    }
    


}

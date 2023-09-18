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
import com.example.secondminiproject.dto.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationViewHolder> {
    private static final String TAG = "ReservationAdapter";
    private List<Reservation> reservationList = new ArrayList<>();
    private List<String> dateList = new ArrayList<>();
    private NavController navController;
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
        Reservation reservation = reservationList.get(position);
        String dateLists = reservation.getImsiReservationDate();
        holder.setData(dateLists);
        
        

        // Step2. 2차 배열 전용 초기 레이아웃 설정 - 예약 내역 목록
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.VERTICAL, false);

        // Step3. 2차 배열 전용 리싸이클 뷰 어댑터 설정 - 예약 내역 목록
        ReservationDetailAdapter reservationDetailAdapter = new ReservationDetailAdapter(reservationList, navController);
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

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public void addReservation(Reservation reservation){
        this.navController = reservation.getReservationNavController();
        reservationList.add(reservation);
        dateList.add(reservation.getImsiReservationDate());
    }




}

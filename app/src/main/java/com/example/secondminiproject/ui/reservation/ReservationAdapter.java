package com.example.secondminiproject.ui.reservation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.Reservation;
import com.example.secondminiproject.dto.ReservationDate;

import java.util.ArrayList;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationViewHolder> {
    private List<ReservationDate> reservationDateList = new ArrayList<>();

    @NonNull
    @Override
    //데이터들을 가지고오는거
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //레이아웃 인플레이터 받는법
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View dateView = layoutInflater.inflate(R.layout.fragment_reservation_list_date, parent, false);
        //괄호안에 inflater 넣어야함
        ReservationViewHolder reservationViewHolder = new ReservationViewHolder(dateView);

        return reservationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder holder, int position) {
        //리스트로부터 데이터를 불러오는것 (position 으로 0 -> 1 - 2 순으로 프로덕트를 가져와서 세팅한다)
        ReservationDate reservationDate = reservationDateList.get(position);
        //홀더에 데이터를 세팅해준다.
        holder.setData(reservationDate);
    }

    public void setReservationList(List<ReservationDate> reservationDateList) {
        this.reservationDateList = reservationDateList;
    }

    @Override
    public int getItemCount() {
        return reservationDateList.size();
    }

    public void addReservation(ReservationDate reservationDate){
        reservationDateList.add(reservationDate);
    }
}

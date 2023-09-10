package com.example.secondminiproject.ui.reservation;

import android.util.Log;
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

public class ReservationDateAdapter extends RecyclerView.Adapter<ReservationDateViewHolder> {
    private static final String TAG = "ReservationDateAdapter";
    private List<Reservation> reservationList = new ArrayList<>();

    @NonNull
    @Override
    //데이터들을 가지고오는거
    public ReservationDateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //레이아웃 인플레이터 받는법
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View cardView = layoutInflater.inflate(R.layout.fragment_reservation_list_card, parent, false);
        //괄호안에 inflater 넣어야함
        ReservationDateViewHolder reservationDateViewHolder = new ReservationDateViewHolder(cardView);

        return reservationDateViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationDateViewHolder holder, int position) {
        //리스트로부터 데이터를 불러오는것 (position 으로 0 -> 1 - 2 순으로 프로덕트를 가져와서 세팅한다)
        Reservation reservation = reservationList.get(position);
        Log.i(TAG, "onBindViewHolder: 여기 온바인트뷰홀더는? "+reservation);
        //홀더에 데이터를 세팅해준다.
        holder.setData(reservation);
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public void addReservation(Reservation reservation){
        reservationList.add(reservation);
        Log.i(TAG, "addReservation: 여기서는 잘나오나?"+reservation);
    }
}

package com.example.secondminiproject.ui.reservation;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.Reservation;

public class ReservationDateViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "ReservationDateViewHold";
    private TextView reservationListTitle;
    private TextView reservationListNumber;
    private TextView reservationListReservationDay;
    private TextView reservationListStartDay;
    private Button reservationListReservationState;

    public ReservationDateViewHolder(@NonNull View itemView) {
        super(itemView);
        this.reservationListTitle = itemView.findViewById(R.id.reservation_list_title);
        this.reservationListNumber = itemView.findViewById(R.id.reservation_list_number);
        this.reservationListReservationDay = itemView.findViewById(R.id.reservation_list_reservation_day);
        this.reservationListStartDay = itemView.findViewById(R.id.reservation_list_start_day);
        this.reservationListReservationState = itemView.findViewById(R.id.reservation_list_reservation_state);
    }

    public void setData(Reservation reservation){
        this.reservationListTitle.setText(reservation.getProductName());
        this.reservationListNumber.setText(reservation.getReservationNo());
        this.reservationListReservationDay.setText(reservation.getImsiReservationDate());
        this.reservationListStartDay.setText(reservation.getStartDate());
        this.reservationListReservationState.setText(reservation.getReservationState());

        Log.i(TAG, "setData: 세팅은 잘되나 ?" + reservation);
    }
}

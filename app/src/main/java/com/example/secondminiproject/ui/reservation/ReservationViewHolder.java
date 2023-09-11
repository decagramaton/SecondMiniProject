package com.example.secondminiproject.ui.reservation;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentReservationListDateBinding;
import com.example.secondminiproject.dto.Reservation;

public class ReservationViewHolder extends RecyclerView.ViewHolder {
    private NavController navController;

    private TextView reservationListDay;
    private TextView reservationListTitle;
    private TextView reservationListNumber;
    private TextView reservationListReservationDay;
    private TextView reservationListStartDay;
    private Button reservationListReservationState;


    public ReservationViewHolder(@NonNull View itemView, ReservationAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);
        this.reservationListDay = itemView.findViewById(R.id.reservation_list_day);
        this.reservationListTitle = itemView.findViewById(R.id.reservation_list_title);
        this.reservationListNumber = itemView.findViewById(R.id.reservation_list_number);
        this.reservationListReservationDay = itemView.findViewById(R.id.reservation_list_reservation_day);
        this.reservationListStartDay = itemView.findViewById(R.id.reservation_list_start_day);
        this.reservationListReservationState = itemView.findViewById(R.id.reservation_list_reservation_state);

        itemView.setOnClickListener(v -> {
            onItemClickListener.onItemClick(v,getAdapterPosition());
        });



    }

    public void setData(Reservation reservation){
        this.reservationListDay.setText(reservation.getImsiReservationDate());
        this.reservationListTitle.setText(reservation.getProductName());
        this.reservationListNumber.setText(String.valueOf(reservation.getReservationNo()));
        this.reservationListReservationDay.setText(reservation.getImsiReservationDate());
        this.reservationListStartDay.setText(reservation.getStartDate());
        this.reservationListReservationState.setText(String.valueOf(reservation.getReservationState()));
    }
}

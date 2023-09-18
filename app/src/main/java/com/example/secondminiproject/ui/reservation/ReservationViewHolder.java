package com.example.secondminiproject.ui.reservation;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentReservationListDateBinding;
import com.example.secondminiproject.dto.Reservation;
import com.example.secondminiproject.ui.home.HomeFragment;

import java.util.Date;

public class ReservationViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "ReservationViewHolder";
    private NavController navController;

    private TextView reservationListDay;
    public RecyclerView recyclerViewReservationListDate;


    public ReservationViewHolder(@NonNull View itemView) {
        super(itemView);
        this.reservationListDay = itemView.findViewById(R.id.reservation_list_day);
        this.recyclerViewReservationListDate = itemView.findViewById(R.id.recycler_view_reservation_list_date);
    }


    public void setData(String reservationDate){

        reservationListDay.setText(reservationDate);
    }
}

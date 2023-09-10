package com.example.secondminiproject.ui.reservation;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.ReservationDate;

import java.text.DecimalFormat;
import java.util.Currency;

public class ReservationViewHolder extends RecyclerView.ViewHolder {

    private TextView reservation_list_day;


    public ReservationViewHolder(@NonNull View itemView) {
        super(itemView);
        this.reservation_list_day = itemView.findViewById(R.id.reservation_list_day);

    }

    public void setData(ReservationDate reservationDate){
        this.reservation_list_day.setText(reservationDate.getImsiReservationDate());

       /* String currencySymbol = Currency.getInstance("KRW").getSymbol();
        DecimalFormat df = new DecimalFormat("#,###");
        this.price.setText(currencySymbol + " " +df.format(product.getPrice()));*/


    }
}

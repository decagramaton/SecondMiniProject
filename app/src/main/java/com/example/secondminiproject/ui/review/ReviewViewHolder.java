package com.example.secondminiproject.ui.review;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.Reservation;

public class ReviewViewHolder extends RecyclerView.ViewHolder {

    private TextView reservation_list_day;


    public ReviewViewHolder(@NonNull View itemView) {
        super(itemView);
        this.reservation_list_day = itemView.findViewById(R.id.reservation_list_day);

    }

    public void setData(Reservation reservation){
        this.reservation_list_day.setText(reservation.getImsiReservationDate());

       /* String currencySymbol = Currency.getInstance("KRW").getSymbol();
        DecimalFormat df = new DecimalFormat("#,###");
        this.price.setText(currencySymbol + " " +df.format(product.getPrice()));*/


    }
}

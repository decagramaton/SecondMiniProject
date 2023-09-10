package com.example.secondminiproject.ui.review;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.ReservationDate;

public class ReviewViewHolder extends RecyclerView.ViewHolder {

    private TextView reservation_list_day;


    public ReviewViewHolder(@NonNull View itemView) {
        super(itemView);
        this.reservation_list_day = itemView.findViewById(R.id.review_title);

    }

    public void setData(ReservationDate reservationDate){
        this.reservation_list_day.setText(reservationDate.getImsiReservationDate());

       /* String currencySymbol = Currency.getInstance("KRW").getSymbol();
        DecimalFormat df = new DecimalFormat("#,###");
        this.price.setText(currencySymbol + " " +df.format(product.getPrice()));*/


    }
}

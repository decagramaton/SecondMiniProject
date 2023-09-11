package com.example.secondminiproject.ui.wish;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.Reservation;
import com.example.secondminiproject.dto.Wish;

public class WishViewHolder extends RecyclerView.ViewHolder {

    private ImageView wishProductImage;
    private TextView wishProductName;
    private TextView wishTourDays;
    private TextView wishProductPrice;


    public WishViewHolder(@NonNull View itemView) {
        super(itemView);
        this.wishProductImage = itemView.findViewById(R.id.wish_product_image);
        this.wishProductName = itemView.findViewById(R.id.wish_product_name);
        this.wishTourDays = itemView.findViewById(R.id.wish_tour_days);
        this.wishProductPrice = itemView.findViewById(R.id.wish_product_price);

    }

    public void setData(Wish wishProduct){
        this.wishProductImage.setImageResource(wishProduct.getProductImage());
        this.wishProductName.setText(wishProduct.getProductName());
        this.wishTourDays.setText(wishProduct.getTourDays());
        this.wishProductPrice.setText(String.valueOf(wishProduct.getProductPrice()));

       /* String currencySymbol = Currency.getInstance("KRW").getSymbol();
        DecimalFormat df = new DecimalFormat("#,###");
        this.price.setText(currencySymbol + " " +df.format(product.getPrice()));*/


    }
}

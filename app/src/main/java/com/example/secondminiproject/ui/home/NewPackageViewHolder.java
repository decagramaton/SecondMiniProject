package com.example.secondminiproject.ui.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.Board;
import com.example.secondminiproject.dto.Review;
import com.example.secondminiproject.service.ProductService;

import java.text.DecimalFormat;
import java.util.Currency;
import java.util.List;

public class NewPackageViewHolder extends RecyclerView.ViewHolder {
    private int productNo;
    private ImageView image;
    private TextView title;
    private TextView price;

    public NewPackageViewHolder(@NonNull View itemView, NewPackageAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);
        this.image = itemView.findViewById(R.id.image);
        this.title = itemView.findViewById(R.id.title);
        this.price = itemView.findViewById(R.id.price);

        itemView.setOnClickListener(v -> {
            onItemClickListener.onItemClick(v, getAdapterPosition());
        });
    }

    public void setData(Board board){
        this.productNo = board.getProductNo();
        this.title.setText(board.getProductTitle());
        ProductService.loadImageByMediaName(this.productNo,"main", image);

        String currencySymbol = Currency.getInstance("KRW").getSymbol();
        DecimalFormat df = new DecimalFormat("#,###");
        this.price.setText(currencySymbol + " " + df.format(board.getProductAdultPrice()));
    }
}


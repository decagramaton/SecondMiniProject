package com.example.secondminiproject.ui.RecentProduct;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.RecentProduct;
import com.example.secondminiproject.dto.Wish;
import com.example.secondminiproject.service.ProductService;
import com.example.secondminiproject.ui.wish.WishAdapter;

import java.text.DecimalFormat;
import java.util.Currency;

public class RecentProductViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "RecentProductViewHolder";

    private ImageView recentProductImage;
    private TextView recentProductName;
    private TextView recentProductReviewNumber;
    private TextView recentProductPrice;
    private RatingBar recentProductRating;


    public RecentProductViewHolder(@NonNull View itemView, RecentProductAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);
        this.recentProductImage = itemView.findViewById(R.id.recent_product_image);
        this.recentProductName = itemView.findViewById(R.id.recent_product_name);
        this.recentProductReviewNumber = itemView.findViewById(R.id.recent_product_review_number);
        this.recentProductPrice = itemView.findViewById(R.id.recent_product_price);
        this.recentProductRating = itemView.findViewById(R.id.recent_product_rating);

        //클릭 이벤트 처리
        itemView.setOnClickListener(v -> {
            onItemClickListener.onItemClick(v, getAdapterPosition());//어뎁터 항목번호
        });
    }

    public void setData(RecentProduct recentProduct){

        Log.i(TAG, "최근본 상품 ViewHolder 호출 이벤트 실행");
        ProductService.loadImageByMediaName(recentProduct.getProductNo(), "main", this.recentProductImage);
        this.recentProductName.setText(recentProduct.getProductName());

        String currencySymbol = Currency.getInstance("KRW").getSymbol();
        DecimalFormat df = new DecimalFormat("#,###");
        this.recentProductPrice.setText(currencySymbol + " " + df.format(recentProduct.getProductPrice()));

        this.recentProductRating.setRating(recentProduct.getProductRating());
        this.recentProductReviewNumber.setText(String.valueOf(recentProduct.getReviewNumber()));

       /* String currencySymbol = Currency.getInstance("KRW").getSymbol();
        DecimalFormat df = new DecimalFormat("#,###");
        this.price.setText(currencySymbol + " " +df.format(product.getPrice()));*/


    }
}

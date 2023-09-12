package com.example.secondminiproject.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.Board;
import com.example.secondminiproject.dto.Product;
import com.example.secondminiproject.dto.Review;
import com.example.secondminiproject.service.ProductService;
import com.example.secondminiproject.service.ServiceProvider;

import java.text.DecimalFormat;
import java.util.Currency;
import java.util.List;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    private int productNo;
    private ImageView image;
    private TextView title;
    private TextView visitPlace;
    private TextView content;
    private TextView price;
    private RatingBar rating;
    private TextView ratingScore;
    private TextView ratingCountByProduct;


    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        this.image = itemView.findViewById(R.id.image);
        this.title = itemView.findViewById(R.id.title);
        this.visitPlace = itemView.findViewById(R.id.visitPlace);
        this.content = itemView.findViewById(R.id.content);
        this.price = itemView.findViewById(R.id.price);
        this.rating = itemView.findViewById(R.id.rating);
        this.ratingScore = itemView.findViewById(R.id.rating_score);
        this.ratingCountByProduct = itemView.findViewById(R.id.rating_count_by_product);
    }

    public void setData(Board board){
        this.productNo = board.getProductNo();
        
        
        // Image 다운 필요
        ProductService.loadImage(this.productNo, image);


        this.title.setText(board.getProductTitle());
        this.visitPlace.setText(board.getProductVisitPlace());
        this.content.setText(board.getProductContent());

        String currencySymbol = Currency.getInstance("KRW").getSymbol();
        DecimalFormat df = new DecimalFormat("#,###");
        this.price.setText(currencySymbol + " " + df.format(board.getProductAdultPrice()));

        // 리뷰 요청 DB 필요
        /*this.rating.setRating(1);
        this.ratingScore.setText(String.valueOf(2));
        this.ratingCountByProduct.setText("(" + 3 + ")");*/

        List<Review> reviewList = board.getReviewList();

        int reviewTotalSum = 0;
        for(Review item : reviewList){
            reviewTotalSum += item.getReviewRating();
        }

        float reviewAverage = (float)(reviewTotalSum/reviewList.size());

        this.rating.setRating(reviewAverage);
        this.ratingScore.setText(String.valueOf(reviewAverage));
        this.ratingCountByProduct.setText("(" + reviewList.size() + ")");
    }
}


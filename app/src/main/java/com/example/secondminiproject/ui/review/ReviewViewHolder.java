package com.example.secondminiproject.ui.review;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.Reservation;
import com.example.secondminiproject.dto.Review;

public class ReviewViewHolder extends RecyclerView.ViewHolder {

    private TextView reviewTitle;
    private RatingBar reviewRatingScore;
    private TextView reviewStartDate;
    //private TextView reviewEndDate;
    private TextView reviewContent;


    public ReviewViewHolder(@NonNull View itemView) {
        super(itemView);
        this.reviewTitle = itemView.findViewById(R.id.review_list_card_review_title);
        this.reviewRatingScore = itemView.findViewById(R.id.review_list_card_star_rate);
        this.reviewStartDate = itemView.findViewById(R.id.review_list_card_travel_start_date);
        //this.reviewEndDate = itemView.findViewById(R.id.review_list_card_travel_end_date);
        this.reviewContent =itemView.findViewById(R.id.review_list_card_review_content);

    }

    public void setData(Review review){
        this.reviewTitle.setText(review.getReviewTitle());
        this.reviewRatingScore.setRating((int)review.getReviewRating());
        this.reviewStartDate.setText(review.getStartDate());
        //this.reviewEndDate.setText(review.getEndDate());
        this.reviewContent.setText(review.getReviewContent());

       /* String currencySymbol = Currency.getInstance("KRW").getSymbol();
        DecimalFormat df = new DecimalFormat("#,###");
        this.price.setText(currencySymbol + " " +df.format(product.getPrice()));*/


    }
}

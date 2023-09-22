package com.example.secondminiproject.ui.review;

import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuProvider;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.Reservation;
import com.example.secondminiproject.dto.Review;
import com.example.secondminiproject.service.ReviewService;
import com.example.secondminiproject.service.ServiceProvider;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "ReviewViewHolder";
    private TextView reviewTitle;
    private RatingBar reviewRatingScore;
    private TextView reviewStartDate;
    private TextView reviewEndDate;
    private TextView reviewContent;

    private androidx.fragment.app.FragmentActivity reviewActivity;
    private androidx.appcompat.widget.Toolbar reviewToolbar;
    private NavController navController;
    private LifecycleOwner lifecycleOwner;
    private int reviewNo;
    private int productNo;

    public ReviewViewHolder(@NonNull View itemView, androidx.fragment.app.FragmentActivity activity, NavController navController, LifecycleOwner lco) {
        super(itemView);

        this.reviewActivity = activity;
        this.navController = navController;
        this.lifecycleOwner =lco;

        this.reviewTitle = itemView.findViewById(R.id.review_list_card_review_title);
        this.reviewRatingScore = itemView.findViewById(R.id.review_list_card_star_rate);
        this.reviewStartDate = itemView.findViewById(R.id.review_list_card_travel_start_date);
        this.reviewEndDate = itemView.findViewById(R.id.review_list_card_travel_end_date);
        this.reviewContent =itemView.findViewById(R.id.review_list_card_review_content);

        this.reviewToolbar = itemView.findViewById(R.id.toolbar_review_list_card);

        reviewToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.toolbar_review_list_card_modify_review) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("reviewNo", reviewNo);
                    bundle.putInt("productNo", productNo);
                    navController.navigate(R.id.dest_review_write, bundle);

                    return true;
                } else if (item.getItemId() == R.id.toolbar_review_list_card_delete_review) {
                    ReviewService reviewService = ServiceProvider.getReviewService(navController.getContext());
                    Call<Void> call = reviewService.removeReview(reviewNo);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            navController.navigate(R.id.dest_review_list);
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });

                    return true;
                }
                return false;
            }
        });
    }

    public void setData(Review review) {
        this.reviewNo =  review.getReviewNo();
        this.reviewTitle.setText(review.getReviewTitle());
        this.reviewRatingScore.setRating((int) review.getReviewRating());
        Date startDate = new Date(review.getTourStartDate());
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY.MM.dd");
        this.reviewStartDate.setText(sdf.format(startDate));
        Date endDate = new Date(review.getTourEndDate());
        this.reviewEndDate.setText(sdf.format(endDate));
        this.reviewContent.setText(review.getReviewContent());
        this.productNo = review.getProductNo();


    }
}

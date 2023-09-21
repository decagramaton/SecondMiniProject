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

public class ReviewViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "ReviewViewHolder";
    private TextView reviewTitle;
    private RatingBar reviewRatingScore;
    private TextView reviewStartDate;
    //private TextView reviewEndDate;
    private TextView reviewContent;

    private androidx.fragment.app.FragmentActivity reviewActivity;
    private androidx.appcompat.widget.Toolbar reviewToolbar;
    private NavController navController;
    private LifecycleOwner lifecycleOwner;

    public ReviewViewHolder(@NonNull View itemView, androidx.fragment.app.FragmentActivity activity, NavController navController, LifecycleOwner lco) {
        super(itemView);

        this.reviewActivity = activity;
        this.navController = navController;
        this.lifecycleOwner =lco;

        this.reviewTitle = itemView.findViewById(R.id.review_list_card_review_title);
        this.reviewRatingScore = itemView.findViewById(R.id.review_list_card_star_rate);
        this.reviewStartDate = itemView.findViewById(R.id.review_list_card_travel_start_date);
        //this.reviewEndDate = itemView.findViewById(R.id.review_list_card_travel_end_date);
        this.reviewContent =itemView.findViewById(R.id.review_list_card_review_content);

        this.reviewToolbar = itemView.findViewById(R.id.toolbar_review_list_card);

        reviewToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.i(TAG, "툴바는 눌리나?");
                if (item.getItemId() == R.id.toolbar_review_list_card_modify_review) {
                    Log.i(TAG, "onMenuItemSelected: 리뷰 수정?");
                    return true;
                } else if (item.getItemId() == R.id.toolbar_review_list_card_delete_review) {
                    Log.i(TAG, "onMenuItemSelected: 리뷰 삭제??");

                    return true;
                }
                return false;
            }
        });
    }

    public void setData(Review review) {
        this.reviewTitle.setText(review.getReviewTitle());
        this.reviewRatingScore.setRating((int) review.getReviewRating());
        this.reviewStartDate.setText(review.getStartDate());
        //this.reviewEndDate.setText(review.getEndDate());
        this.reviewContent.setText(review.getReviewContent());



    }
}

package com.example.secondminiproject.service;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.secondminiproject.dto.Board;
import com.example.secondminiproject.dto.Review;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReviewService {
        @GET("review/getReviewListByUserNo")
        Call<List<Review>> getReviewListByUserNo(@Query("userNo") int userNo);

}

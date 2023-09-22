package com.example.secondminiproject.service;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.secondminiproject.dto.Board;
import com.example.secondminiproject.dto.Review;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ReviewService {
        @GET("review/getReviewListByUserNo")
        Call<List<Review>> getReviewListByUserNo(@Query("userNo") int userNo);

        @GET("review/addReview")
        Call<Void> addReview(@Query("title") String title, @Query("rating") int rating, @Query("content") String content,
                             @Query("reservationNo") int reservationNo, @Query("userNo") int userNo, @Query("productNo") int productNo);

        @GET("review/checkReview")
        Call<Integer> checkReview(@Query("reservationNo") int reservationNo);

        @GET("review/removeReview")
        Call<Void> removeReview(@Query("reviewNo") int reviewNo);

        @POST("review/updateReview")
        Call<Void> updateReview(@Query("reviewNo") int reviewNo,
                                @Query("rating") int rating,
                                @Query("content") String content);
}

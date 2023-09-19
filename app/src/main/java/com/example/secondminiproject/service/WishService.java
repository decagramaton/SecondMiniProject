package com.example.secondminiproject.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WishService {
    @GET("wish/checkWishByUserNoAndProductNo")
    Call<Integer> checkWishByUserNoAndProductNo(@Query("productNo") int productNo,@Query("userNo") int userNo);

    @GET("wish/clickWishBtn")
    Call<Void> clickWishBtn(@Query("productNo") int productNo,@Query("userNo") int userNo);
}

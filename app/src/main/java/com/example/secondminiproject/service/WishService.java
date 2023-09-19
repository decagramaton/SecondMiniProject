package com.example.secondminiproject.service;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WishService {
    @GET("wish/checkWishByUserNoAndProductNo")
    Call<Integer> checkWishByUserNoAndProductNo(int productNo, int userNo);
}

package com.example.secondminiproject.service;

import android.content.Context;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceProvider {
    private static final String TAG = "ServiceProvider";

    public static Retrofit getRetrofit(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkInfo.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static ProductService getProductService(Context context) {
        ProductService productService = getRetrofit(context).create(ProductService.class);
        return productService;
    }

    public static WishService getWishService(Context context) {
        WishService wishService = getRetrofit(context).create(WishService.class);
        return wishService;
    }

    public static ReviewService getReviewService(Context context) {
        ReviewService reviewService = getRetrofit(context).create(ReviewService.class);
        return reviewService;
    }


}

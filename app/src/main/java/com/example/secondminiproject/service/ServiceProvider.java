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
        return getRetrofit(context).create(ProductService.class);
    }

    public static WishService getWishService(Context context) {
        return getRetrofit(context).create(WishService.class);
    }

    public static ReviewService getReviewService(Context context) {
        return getRetrofit(context).create(ReviewService.class);
    }

    public static UserService getUserService(Context context) {
        return getRetrofit(context).create(UserService.class);
    }

    public static ReservationService getReservationService(Context context) {
        return getRetrofit(context).create(ReservationService.class);
    }

}

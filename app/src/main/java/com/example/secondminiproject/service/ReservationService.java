package com.example.secondminiproject.service;

import com.example.secondminiproject.dto.Reservation;
import com.example.secondminiproject.dto.Review;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ReservationService {
        @GET("reservation/setNewReservationInfo")
        Call<Void> setNewReservationInfo(@Query("userNo") int userNo, @Query("productNo") int productNo,
                                         @Query("adultNumber") int adultNumber, @Query("childNumber") int childrenNumber);

        @GET("reservation/getReservationDayList")
        Call<List<Long>> getReservationDayList(@Query("userNo") int userNo);

        /*@GET("reservation/getReservationListByDay")
        Call<List<Reservation>> getReservationListByDay(@Query("reservationDate") Date reservationDate, @Query("userNo") int userNo);*/

        @GET("reservation/getReservationListByDay")
        Call<List<Reservation>> getReservationListByDay(@Query("reservationDate") long reservationDate, @Query("userNo") int userNo);

        @GET("reservation/reservationCancel")
        Call<Void> reservationCancel(@Query("reservationNo") int reservationNo, @Query("userNo") int userNo);

        @GET("reservation/getThisProductReservationsNo")
        Call<Integer> getThisProductReservationsNo(@Query("productNo") int productNo);
}

package com.example.secondminiproject.service;

import com.example.secondminiproject.dto.LoginResult;
import com.example.secondminiproject.dto.Review;
import com.example.secondminiproject.dto.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    /**
     * 로그인 요청 및 로그인 결과를 반환하는 메소드
     * @return LoginResult
     */
    @POST("userInfo/login")
    Call<LoginResult> login(@Query("userId") String userId, @Query("userPassword") String userPassword);



    @GET("userInfo/getUserInfo")
    Call<UserInfo> getUserInfo(@Query("userId") String userId);
    
    
}

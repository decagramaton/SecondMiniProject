package com.example.secondminiproject.service;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.secondminiproject.dto.Board;
import com.example.secondminiproject.dto.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {
        @GET("product/getProductList")
        Call<List<Board>> getProductList();


        static void loadImage(int productNo, ImageView imageView) {
                String url = NetworkInfo.BASE_URL + "product/fileDownload?productNo=" + productNo;
                Glide.with(imageView.getContext()).load(url).into(imageView);
        }
}

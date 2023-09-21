package com.example.secondminiproject.service;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.secondminiproject.dto.Board;
import com.example.secondminiproject.dto.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductService {
        @GET("product/getProductList")
        Call<List<Board>> getProductList(@Query("requestType") String requestType);

        @GET("product/getProductListBySearchKeyword")
        Call<List<Board>> getProductListBySearchKeyword(@Query("searchKeyword") String searchKeyword);

        @GET("product/getProductListByCategory")
        Call<List<Board>> getProductListByCategory(@Query("category") String category);

        @GET("product/getProductByProductNo")
        Call<Board> getProductByProductNo(@Query("productNo") int productNo);

        static void loadImageByMediaName(int productNo ,String mediaName, ImageView imageView) {
                String url = NetworkInfo.BASE_URL + "product/fileDownload?productNo=" + productNo+"&mediaName="+mediaName;
                Glide.with(imageView.getContext()).load(url).into(imageView);
        }
}

package com.example.secondminiproject.dto;

import android.media.Rating;

import java.io.Serializable;

public class RecentProduct implements Serializable {
    private String productName;
    private int reviewNumber;
    private int productNo;
    private int productPrice;
    private float productRating;

    @Override
    public String toString() {
        return "RecentProduct{" +
                "productName='" + productName + '\'' +
                ", reviewNumber='" + reviewNumber + '\'' +
                ", productNo=" + productNo +
                ", productPrice=" + productPrice +
                ", productRating=" + productRating +
                '}';
    }

    public int getReviewNumber() {
        return reviewNumber;
    }

    public void setReviewNumber(int reviewNumber) {
        this.reviewNumber = reviewNumber;
    }

    public float getProductRating() {
        return productRating;
    }

    public void setProductRating(float productRating) {
        this.productRating = productRating;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
}

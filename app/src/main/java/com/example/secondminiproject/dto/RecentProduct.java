package com.example.secondminiproject.dto;

import android.media.Rating;

import java.io.Serializable;

public class RecentProduct implements Serializable {
    private String productName;
    private int reviewNumber;
    private int productImage;
    private int productPrice;
    private float productRating;

    @Override
    public String toString() {
        return "RecentProduct{" +
                "productName='" + productName + '\'' +
                ", reviewNumber='" + reviewNumber + '\'' +
                ", productImage=" + productImage +
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

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
}

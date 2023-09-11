package com.example.secondminiproject.dto;

public class Wish {
    private String productName;
    private String tourDays;
    private int productImage;
    private int productPrice;

    @Override
    public String toString() {
        return "Wish{" +
                "productName='" + productName + '\'' +
                ", tourDays='" + tourDays + '\'' +
                ", productImage=" + productImage +
                ", productPrice=" + productPrice +
                '}';
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTourDays() {
        return tourDays;
    }

    public void setTourDays(String tourDays) {
        this.tourDays = tourDays;
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

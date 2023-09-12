package com.example.secondminiproject.dto;

import java.util.Date;
import java.util.List;

// Product DB DTO + Review DB DTO 통합용 DTO입니다.
public class Board {
    private int productNo;
    private String productTitle;
    private int productAdultPrice;
    private int productChildPrice;
    private long tourStartDate;
    private long tourEndDate;
    private String productVehicle;
    private String productVisitPlace;
    private int productReservationNumber;
    private String productContent;
    private String productCategory;
    List<Review> reviewList;


    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getProductAdultPrice() {
        return productAdultPrice;
    }

    public void setProductAdultPrice(int productAdultPrice) {
        this.productAdultPrice = productAdultPrice;
    }

    public int getProductChildPrice() {
        return productChildPrice;
    }

    public void setProductChildPrice(int productChildPrice) {
        this.productChildPrice = productChildPrice;
    }

    public long getTourStartDate() {
        return tourStartDate;
    }

    public void setTourStartDate(long tourStartDate) {
        this.tourStartDate = tourStartDate;
    }

    public long getTourEndDate() {
        return tourEndDate;
    }

    public void setTourEndDate(long tourEndDate) {
        this.tourEndDate = tourEndDate;
    }

    public String getProductVehicle() {
        return productVehicle;
    }

    public void setProductVehicle(String productVehicle) {
        this.productVehicle = productVehicle;
    }

    public String getProductVisitPlace() {
        return productVisitPlace;
    }

    public void setProductVisitPlace(String productVisitPlace) {
        this.productVisitPlace = productVisitPlace;
    }

    public int getProductReservationNumber() {
        return productReservationNumber;
    }

    public void setProductReservationNumber(int productReservationNumber) {
        this.productReservationNumber = productReservationNumber;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
}

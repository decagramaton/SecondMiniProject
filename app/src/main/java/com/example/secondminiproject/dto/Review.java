package com.example.secondminiproject.dto;

import java.util.Date;

public class Review {
    private int reviewNo;
    private int productNo;
    private int reservationNo;
    private int userNo;
    private String reviewTitle;
    private String reviewContent;
    private long reviewDate;
    private int reviewRating;

    //----임시데이터
    private String startDate;
    private String endDate;

    @Override
    public String toString() {
        return "Review{" +
                "reviewNo=" + reviewNo +
                ", productNo=" + productNo +
                ", reservationNo=" + reservationNo +
                ", userNo=" + userNo +
                ", reviewTitle='" + reviewTitle + '\'' +
                ", reviewContent='" + reviewContent + '\'' +
                ", reviewDate=" + reviewDate +
                ", reviewRating=" + reviewRating +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getReviewNo() {
        return reviewNo;
    }

    public void setReviewNo(int reviewNo) {
        this.reviewNo = reviewNo;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public int getReservationNo() {
        return reservationNo;
    }

    public void setReservationNo(int reservationNo) {
        this.reservationNo = reservationNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public long getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(long reviewDate) {
        this.reviewDate = reviewDate;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }
}

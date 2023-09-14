package com.example.secondminiproject.dto;

import android.provider.ContactsContract;

import androidx.navigation.NavController;

import java.io.Serializable;
import java.util.Date;

public class Reservation implements Serializable {

    private int reservationNo;
    private int userNo;
    private int productNo;
    private int reservationAdultNumber;
    private int reservationChildNumber;
    private Date reservationDate;
    private int reservationState;

    //-------------DB 얻기 전까지 임시로
    private String productName;
    private int reservationImage;
    private String startDate;
    private String endDate;

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationNo=" + reservationNo +
                ", userNo=" + userNo +
                ", productNo=" + productNo +
                ", reservationAdultNumber=" + reservationAdultNumber +
                ", reservationChildNumber=" + reservationChildNumber +
                ", reservationDate=" + reservationDate +
                ", reservationState=" + reservationState +
                ", productName='" + productName + '\'' +
                ", reservationImage=" + reservationImage +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", imsiReservationDate='" + imsiReservationDate + '\'' +
                ", reservationNavController=" + reservationNavController +
                '}';
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    private String imsiReservationDate;

    private NavController reservationNavController;

    public NavController getReservationNavController() {
        return reservationNavController;
    }

    public void setReservationNavController(NavController reservationNavController) {
        this.reservationNavController = reservationNavController;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getImsiReservationDate() {
        return imsiReservationDate;
    }

    public void setImsiReservationDate(String imsiReservationDate) {
        this.imsiReservationDate = imsiReservationDate;
    }

    public int getReservationImage() {
        return reservationImage;
    }

    public void setReservationImage(int reservationImage) {
        this.reservationImage = reservationImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public int getReservationAdultNumber() {
        return reservationAdultNumber;
    }

    public void setReservationAdultNumber(int reservationAdultNumber) {
        this.reservationAdultNumber = reservationAdultNumber;
    }

    public int getReservationChildNumber() {
        return reservationChildNumber;
    }

    public void setReservationChildNumber(int reservationChildNumber) {
        this.reservationChildNumber = reservationChildNumber;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getReservationState() {
        return reservationState;
    }

    public void setReservationState(int reservationState) {
        this.reservationState = reservationState;
    }
}

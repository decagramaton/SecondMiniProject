package com.example.secondminiproject.dto;

public class Product {
    private int pno;
    private int image;
    private String title;
    private String subTitle;
    private String content;
    private int price;
    private int rating;
    private int ratingCountByProduct;

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRatingCountByProduct() {
        return ratingCountByProduct;
    }

    public void setRatingCountByProduct(int ratingCountByProduct) {
        this.ratingCountByProduct = ratingCountByProduct;
    }
}

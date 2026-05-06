package com.example.session4.model.DTO;

public class BookRequest {
    private Long authorID;
    private String title;
    private Double price;

    public BookRequest() {
    }

    public BookRequest(Long authorID, String title, Double price) {
        this.authorID = authorID;
        this.title = title;
        this.price = price;
    }

    public Long getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Long authorID) {
        this.authorID = authorID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

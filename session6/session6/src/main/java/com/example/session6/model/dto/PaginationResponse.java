package com.example.session6.model.dto;

import com.example.session6.model.entity.Patient;

import java.util.List;

public class PaginationResponse {
    //data (List<Patient>), totalPage, totalElement, currentPage
    private List<Patient> data;
    private int totalPage;
    private int totalElement;
    private int currentPage;

    public PaginationResponse() {
    }

    public PaginationResponse(List<Patient> data, int totalPage, int totalElement, int currentPage) {
        this.data = data;
        this.totalPage = totalPage;
        this.totalElement = totalElement;
        this.currentPage = currentPage;
    }

    public List<Patient> getData() {
        return data;
    }

    public void setData(List<Patient> data) {
        this.data = data;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(int totalElement) {
        this.totalElement = totalElement;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}

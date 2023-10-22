package com.starter.project.dto;


import java.util.List;

public class PagebaleDataDto<T> {
    List<T> data;
    int total;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

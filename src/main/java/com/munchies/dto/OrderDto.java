package com.munchies.dto;

import java.util.Date;

public class OrderDto {

    private Long id;

    private String creator;

    private Date orderTimeout;

    private String orderUrl;

    public OrderDto() {
    }

    public OrderDto(Long id, String creator, Date orderTimeout, String orderUrl) {
        this.id = id;
        this.creator = creator;
        this.orderTimeout = orderTimeout;
        this.orderUrl = orderUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getOrderTimeout() {
        return orderTimeout;
    }

    public void setOrderTimeout(Date orderTimeout) {
        this.orderTimeout = orderTimeout;
    }

    public String getOrderUrl() {
        return orderUrl;
    }

    public void setOrderUrl(String orderUrl) {
        this.orderUrl = orderUrl;
    }
}

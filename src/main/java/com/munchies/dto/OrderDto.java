package com.munchies.dto;

import com.munchies.model.OrderItem;
import com.munchies.model.Restaurant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDto {

    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    private String creator;

    private Date orderTimeout;

    private String orderUrl;

    private Restaurant restaurant;

    private List<OrderItemDto> orderItems = new ArrayList<>();



    public OrderDto() {
    }

    public OrderDto(Long id, @NotNull @Size(min = 3, max = 50) String creator, Date orderTimeout, String orderUrl, Restaurant restaurant, List<OrderItemDto> orderItems) {
        this.id = id;
        this.creator = creator;
        this.orderTimeout = orderTimeout;
        this.orderUrl = orderUrl;
        this.restaurant = restaurant;
        this.orderItems = orderItems;
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }
}

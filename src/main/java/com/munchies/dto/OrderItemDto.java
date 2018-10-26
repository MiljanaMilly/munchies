package com.munchies.dto;

import com.munchies.model.Order;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrderItemDto {

    private Long id;

    //    @NotNull
    @Size(min = 3, max = 50)
    private String creator;

    //    @NotNull
    @Size(min = 4, max = 50)
    private String name;

    //    @NotNull
    private Double price;

    private Long orderId;

    public OrderItemDto() {
    }

    public OrderItemDto(Long id, String creator, String name, Double price) {
        this.id = id;
        this.creator = creator;
        this.name = name;
        this.price = price;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}

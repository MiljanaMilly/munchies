package com.munchies.dto;

public class OrderItemDto {

    private Long id;

    private String creator;

    private String name;

    private Double price;

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
}

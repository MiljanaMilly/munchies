package com.munchies.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "creator")
    @NotNull
    @Size(min = 3, max = 50)
    private String creator;

    @Column(name = "name")
    @NotNull
    @Size(min = 4, max = 50)
    private String name;

    @Column(name = "price")
    @NotNull
    private Double price;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Order order;

    public OrderItem() {
    }

    public OrderItem(@NotNull @Size(min = 3, max = 50) String creator, @NotNull @Size(min = 4, max = 50) String name, @NotNull Double price, Order order) {
        this.creator = creator;
        this.name = name;
        this.price = price;
        this.order = order;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

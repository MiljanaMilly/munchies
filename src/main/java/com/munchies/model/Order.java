package com.munchies.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "creator")
    private String creator;

    @Column(name = "order_timeout")
    private Date orderTimeout;

    @Column(name = "order_url")
    private String orderUrl;

    @Column(name = "sent_email")
    private Integer sentEmail;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order() {
    }

    public Order(String creator, Date orderTimeout, String orderUrl, Integer sentEmail, Restaurant restaurant, List<OrderItem> orderItems) {
        this.creator = creator;
        this.orderTimeout = orderTimeout;
        this.orderUrl = orderUrl;
        this.sentEmail = sentEmail;
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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Integer getSentEmail() {
        return sentEmail;
    }

    public void setSentEmail(Integer sentEmail) {
        this.sentEmail = sentEmail;
    }
}

package com.munchies.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "group_order")
public class GroupOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long group_order_id;

    @Column(name = "creator", nullable = false)
    private String creator;

    @Column(name = "order_timeout", nullable = false)
    private Integer order_timeout;

    @Column(name = "order_url", nullable = false)
    private String order_url;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "groupOrder")
    private List<Order> order;

    public GroupOrder() {
    }

    public GroupOrder(String creator, Integer order_timeout, String order_url, Restaurant restaurant, List<Order> order) {
        this.creator = creator;
        this.order_timeout = order_timeout;
        this.order_url = order_url;
        this.restaurant = restaurant;
        this.order = order;
    }

    public Long getGroup_order_id() {
        return group_order_id;
    }

    public void setGroup_order_id(Long group_order_id) {
        this.group_order_id = group_order_id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getOrder_timeout() {
        return order_timeout;
    }

    public void setOrder_timeout(Integer order_timeout) {
        this.order_timeout = order_timeout;
    }

    public String getOrder_url() {
        return order_url;
    }

    public void setOrder_url(String order_url) {
        this.order_url = order_url;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }
}

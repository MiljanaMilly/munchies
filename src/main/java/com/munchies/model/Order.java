package com.munchies.model;


import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long order_id;

    @Column(name = "order_creator", nullable = false)
    private String orderCreator;

    @Column(name = "order_item", nullable = false)
    private String orderItem;

    @ManyToOne
    @JoinColumn(name = "group_order_id")
    private GroupOrder groupOrder;

    public Order() {
    }

    public Order(String orderCreator, String orderItem) {
        this.orderCreator = orderCreator;
        this.orderItem = orderItem;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public String getOrderCreator() {
        return orderCreator;
    }

    public void setOrderCreator(String orderCreator) {
        this.orderCreator = orderCreator;
    }

    public String getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(String orderItem) {
        this.orderItem = orderItem;
    }
}

package com.munchies.dto;

import com.munchies.model.Order;
import com.munchies.validators.UniqueRestaurantValidator;
import com.munchies.validators.ValidationOnInsert;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDto {


    private Long id;

    @Size(min = 5, max = 50)
    @NotNull
    @UniqueRestaurantValidator(groups = {ValidationOnInsert.class})
    private String name;

    @Size(min = 5, max = 50)
    @NotNull
    private String address;

    @Size(min = 6, max = 15)
    @NotNull
//  @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$")
    //format +(123) - 456-78-90
    private String phoneNumber;

    @NotNull
    @Size(max = 500)
//    @Pattern(regexp = "^(?:http(s)?:\\/\\/)?[\\w.-]+(?:\\.[\\w\\.-]+)+[\\w\\-\\._~:/?#[\\]@!\\$&'\\(\\)\\*\\+,;=.]+$")
    private String menuUrl;

    @Size(max = 50)
    private String deliveryTime;

    @Size(max = 100)
    private String additionalCharges;

    @Size(max = 250)
    private String deliveryInfo;

    private List<OrderDto> orders = new ArrayList<>();

    public RestaurantDto() {
    }

    public RestaurantDto(Long id, String name, String address, String phoneNumber, String menuUrl, String deliveryTime, String additionalCharges, String deliveryInfo, List<OrderDto> orders) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.menuUrl = menuUrl;
        this.deliveryTime = deliveryTime;
        this.additionalCharges = additionalCharges;
        this.deliveryInfo = deliveryInfo;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getAdditionalCharges() {
        return additionalCharges;
    }

    public void setAdditionalCharges(String additionalCharges) {
        this.additionalCharges = additionalCharges;
    }

    public String getDeliveryInfo() {
        return deliveryInfo;
    }

    public void setDeliveryInfo(String deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDto> orders) {
        this.orders = orders;
    }

    public OrderDto addOrder(OrderDto order) {
        orders.add(order);
        return order;
    }
}

package com.munchies.dto;

import com.munchies.model.Order;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetailsDto {

    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

    private String menuUrl;


    private String deliveryTime;

    private String additionalCharges;

    private String deliveryInfo;

    public RestaurantDetailsDto() {
    }

    public RestaurantDetailsDto(Long id, String name, String address, String phoneNumber, String menuUrl, String deliveryTime, String additionalCharges, String deliveryInfo) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.menuUrl = menuUrl;
        this.deliveryTime = deliveryTime;
        this.additionalCharges = additionalCharges;
        this.deliveryInfo = deliveryInfo;
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
}

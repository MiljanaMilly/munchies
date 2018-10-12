package com.munchies.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long restaurant_id;


    @Column(name = "name")
    @Size(min = 10, max = 50)
    @NotNull
    private String name;

    @Size(min = 10, max = 50)
    @NotNull
    @Column(name = "address")
    private String address;

    @Size(min = 6, max = 15)
    @NotNull
    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$")
    //format +(123) - 456-78-90
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "menu_url")
    @NotNull
    @Size(max = 500)
    @Pattern(regexp = "^(?:http(s)?:\\/\\/)?[\\w.-]+(?:\\.[\\w\\.-]+)+[\\w\\-\\._~:/?#[\\]@!\\$&'\\(\\)\\*\\+,;=.]+$")
    private String menuURL;

    @OneToMany(mappedBy = "restaurant")
    private List<GroupOrder> groupOrder;

    @Column(name = "delivery_time")
    @NotNull
    @Size(max = 50)
    private String deliveryTime;

    @Column(name = "additional_charges")
    @Size(max = 100)
    private String additionalCharges;

    @Column(name = "delivery_info")
    @Size(max = 250)
    private String deliveryInfo;

    public Restaurant() {
    }

    public Restaurant(String name, String address, String phoneNumber, String menuURL, String deliveryTime, String additionalCharges, String deliveryInfo) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.menuURL = menuURL;
        this.deliveryTime = deliveryTime;
        this.additionalCharges = additionalCharges;
        this.deliveryInfo = deliveryInfo;
    }

    public Long getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Long restaurant_id) {
        this.restaurant_id = restaurant_id;
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

    public String getMenuURL() {
        return menuURL;
    }

    public void setMenuURL(String menuURL) {
        this.menuURL = menuURL;
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

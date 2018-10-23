package com.munchies.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;


    @Column(name = "name")
    @Size(min = 5, max = 50)
    @NotNull
    private String name;

    @Size(min = 5, max = 50)
    @NotNull
    @Column(name = "address")
    private String address;

    @Size(min = 6, max = 15)
    @NotNull
//  @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$")
    //format +(123) - 456-78-90
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "menu_url")
    @NotNull
    @Size(max = 500)
//    @Pattern(regexp = "^(?:http(s)?:\\/\\/)?[\\w.-]+(?:\\.[\\w\\.-]+)+[\\w\\-\\._~:/?#[\\]@!\\$&'\\(\\)\\*\\+,;=.]+$")
    private String menuUrl;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @Column(name = "delivery_time")
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

    public Restaurant(@Size(min = 5, max = 50) @NotNull String name, @Size(min = 5, max = 50) @NotNull String address, @Size(min = 6, max = 15) @NotNull String phoneNumber, @NotNull @Size(max = 500) String menuUrl, List<Order> orders, @Size(max = 50) String deliveryTime, @Size(max = 100) String additionalCharges, @Size(max = 250) String deliveryInfo) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.menuUrl = menuUrl;
        this.orders = orders;
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
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

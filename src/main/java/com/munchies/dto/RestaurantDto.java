package com.munchies.dto;


import com.munchies.validators.FileSizeValidator;
import com.munchies.validators.UniqueRestaurantValidator;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@UniqueRestaurantValidator
public class RestaurantDto {


    private Long id;

    @Size(min = 5, max = 50)
    @NotNull
    private String name;

    @Size(min = 5, max = 50)
    @NotNull
    private String address;

    @Size(min = 6, max = 15)
    @NotNull
//  @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$")
//  format +(123) - 456-78-90
    private String phoneNumber;

    //  @NotNull
    @Size(min = 10, max = 500)
//  @Pattern(regexp = "^(?:http(s)?:\\/\\/)?[\\w.-]+(?:\\.[\\w\\.-]+)+[\\w\\-\\._~:/?#[\\]@!\\$&'\\(\\)\\*\\+,;=.]+$")
    private String menuUrl;

    @Size(min = 10, max = 30)
    @Email
    private String email;

    @Size(max = 50)
    private String deliveryTime;

    @Size(max = 100)
    private String additionalCharges;

    @Size(max = 250)
    private String deliveryInfo;

    //custom validators
    @FileSizeValidator
    private MultipartFile file;

    private List<OrderDto> orders = new ArrayList<>();

    public RestaurantDto() {
    }

    public RestaurantDto(Long id, @Size(min = 5, max = 50) @NotNull String name, @Size(min = 5, max = 50) @NotNull String address, @Size(min = 6, max = 15) @NotNull String phoneNumber, @Size(min = 10, max = 500) String menuUrl, @Size(min = 10, max = 30) @Email String email, @Size(max = 50) String deliveryTime, @Size(max = 100) String additionalCharges, @Size(max = 250) String deliveryInfo, List<OrderDto> orders) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.menuUrl = menuUrl;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}

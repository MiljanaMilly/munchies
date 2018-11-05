package com.munchies.services.dtoMappers;

import com.munchies.dto.RestaurantDto;
import com.munchies.model.Restaurant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantMapper {
//save restaurant
//edit restaurant
public Restaurant mapRestDtoToEntityWithOrders(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantDto.getId());
        restaurant.setName(restaurantDto.getName());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setPhoneNumber(restaurantDto.getPhoneNumber());
        restaurant.setMenuUrl(restaurantDto.getMenuUrl());
    restaurant.setEmail(restaurantDto.getEmail());
        restaurant.setAdditionalCharges(restaurantDto.getAdditionalCharges());
        restaurant.setDeliveryInfo(restaurantDto.getDeliveryInfo());
        restaurant.setDeliveryTime(restaurantDto.getDeliveryTime());
        restaurant.setOrders(new OrderMapper().mapDtoOrdersToEntities(restaurantDto.getOrders()));
        return restaurant;
    }
//group order
public Restaurant mapDtosToEntityNoOrdersNoRest(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantDto.getId());
        restaurant.setName(restaurantDto.getName());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setPhoneNumber(restaurantDto.getPhoneNumber());
        restaurant.setMenuUrl(restaurantDto.getMenuUrl());
    restaurant.setEmail(restaurantDto.getEmail());
        restaurant.setAdditionalCharges(restaurantDto.getAdditionalCharges());
        restaurant.setDeliveryInfo(restaurantDto.getDeliveryInfo());
        restaurant.setDeliveryTime(restaurantDto.getDeliveryTime());
        return restaurant;
    }
//go to restaurants page
//save restaurant
//edit restaurant
//home restaurants
// create new group order
// create new group order - post
public RestaurantDto mapEntityToRestDtoNoOrdersNoRest(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setName(restaurant.getName());
        restaurantDto.setAddress(restaurant.getAddress());
        restaurantDto.setPhoneNumber(restaurant.getPhoneNumber());
        restaurantDto.setMenuUrl(restaurant.getMenuUrl());
    restaurantDto.setEmail(restaurant.getEmail());
        restaurantDto.setAdditionalCharges(restaurant.getAdditionalCharges());
        restaurantDto.setDeliveryInfo(restaurant.getDeliveryInfo());
        restaurantDto.setDeliveryTime(restaurant.getDeliveryTime());
        return restaurantDto;
    }

    public RestaurantDto mapEntityToDtoWithRest(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setName(restaurant.getName());
        restaurantDto.setAddress(restaurant.getAddress());
        restaurantDto.setPhoneNumber(restaurant.getPhoneNumber());
        restaurantDto.setMenuUrl(restaurant.getMenuUrl());
        restaurantDto.setEmail(restaurant.getEmail());
        restaurantDto.setAdditionalCharges(restaurant.getAdditionalCharges());
        restaurantDto.setDeliveryInfo(restaurant.getDeliveryInfo());
        restaurantDto.setDeliveryTime(restaurant.getDeliveryTime());
        restaurantDto.setOrders(new OrderMapper().mapEntitiesToDtos(restaurant.getOrders()));
        return restaurantDto;
    }

}

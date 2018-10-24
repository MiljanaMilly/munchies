package com.munchies.services.dtoMappers;

import com.munchies.dto.RestaurantDetailsDto;
import com.munchies.dto.RestaurantListDto;
import com.munchies.model.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {

    public Restaurant mapRestListToEntity(RestaurantListDto restaurantListDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantListDto.getId());
        restaurant.setName(restaurantListDto.getName());
        restaurant.setAddress(restaurantListDto.getAddress());
        restaurant.setPhoneNumber(restaurantListDto.getPhoneNumber());
        restaurant.setMenuUrl(restaurantListDto.getMenuUrl());
        return restaurant;
    }

    public RestaurantListDto mapRestToRestListDto(Restaurant restaurant) {
        RestaurantListDto restListDto = new RestaurantListDto();
        restListDto.setId(restaurant.getId());
        restListDto.setName(restaurant.getName());
        restListDto.setAddress(restaurant.getAddress());
        restListDto.setPhoneNumber(restaurant.getPhoneNumber());
        restListDto.setMenuUrl(restaurant.getMenuUrl());
        return restListDto;
    }

    public Restaurant mapRestDetailsToEntity(RestaurantDetailsDto restaurantDetailsDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantDetailsDto.getId());
        restaurant.setName(restaurantDetailsDto.getName());
        restaurant.setAddress(restaurantDetailsDto.getAddress());
        restaurant.setPhoneNumber(restaurantDetailsDto.getPhoneNumber());
        restaurant.setMenuUrl(restaurantDetailsDto.getMenuUrl());
        restaurant.setAdditionalCharges(restaurantDetailsDto.getAdditionalCharges());
        restaurant.setDeliveryTime(restaurantDetailsDto.getDeliveryTime());
        restaurant.setDeliveryInfo(restaurantDetailsDto.getDeliveryInfo());
        return restaurant;
    }

    public RestaurantDetailsDto mapRestToRestDetailsDto(Restaurant restaurant) {
        RestaurantDetailsDto restDetailsDto = new RestaurantDetailsDto();
        restDetailsDto.setId(restaurant.getId());
        restDetailsDto.setName(restaurant.getName());
        restDetailsDto.setAddress(restaurant.getAddress());
        restDetailsDto.setPhoneNumber(restaurant.getPhoneNumber());
        restDetailsDto.setMenuUrl(restaurant.getMenuUrl());
        restDetailsDto.setAdditionalCharges(restaurant.getAdditionalCharges());
        restDetailsDto.setDeliveryInfo(restaurant.getDeliveryInfo());
        restDetailsDto.setDeliveryTime(restaurant.getDeliveryTime());
        return restDetailsDto;
    }
}

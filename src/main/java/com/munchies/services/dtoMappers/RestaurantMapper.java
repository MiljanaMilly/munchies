package com.munchies.services.dtoMappers;

import com.munchies.dto.RestaurantDto;
import com.munchies.model.Restaurant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantMapper {

    public Restaurant mapRestDtoToEntity(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantDto.getId());
        restaurant.setName(restaurantDto.getName());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setPhoneNumber(restaurantDto.getPhoneNumber());
        restaurant.setMenuUrl(restaurantDto.getMenuUrl());
        restaurant.setAdditionalCharges(restaurantDto.getAdditionalCharges());
        restaurant.setDeliveryInfo(restaurantDto.getDeliveryInfo());
        restaurant.setDeliveryTime(restaurantDto.getDeliveryTime());
        restaurant.setOrders(new OrderMapper().mapDtoOrdersToEntities(restaurantDto.getOrders()));
        return restaurant;
    }

    public RestaurantDto mapEntityToRestDto(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setName(restaurant.getName());
        restaurantDto.setAddress(restaurant.getAddress());
        restaurantDto.setPhoneNumber(restaurant.getPhoneNumber());
        restaurantDto.setMenuUrl(restaurant.getMenuUrl());
        restaurantDto.setAdditionalCharges(restaurant.getAdditionalCharges());
        restaurantDto.setDeliveryInfo(restaurant.getDeliveryInfo());
        restaurantDto.setDeliveryTime(restaurant.getDeliveryTime());
        restaurantDto.setOrders(new OrderMapper().mapEntitiesToDtoOrders(restaurant.getOrders()));
        return restaurantDto;
    }

    public List<Restaurant> mapDtoListToEntityList(List<RestaurantDto> restaurantDtoList) {
        List<Restaurant> restaurantList = new ArrayList<>();
        RestaurantMapper restaurantMapper = new RestaurantMapper();
        for (RestaurantDto r : restaurantDtoList) {
            restaurantList.add(restaurantMapper.mapRestDtoToEntity(r));
        }
        return restaurantList;
    }

    public List<RestaurantDto> mapEntitiesToDtoList(List<Restaurant> restaurantList) {
        List<RestaurantDto> restaurantDtos = new ArrayList<>();
        RestaurantMapper restaurantMapper = new RestaurantMapper();
        for (Restaurant r : restaurantList) {
            restaurantDtos.add(restaurantMapper.mapEntityToRestDto(r));
        }
        return restaurantDtos;


    }

}

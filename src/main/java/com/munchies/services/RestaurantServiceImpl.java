package com.munchies.services;

import com.munchies.model.GroupOrder;
import com.munchies.model.Restaurant;
import com.munchies.repositories.GroupOrderRepository;
import com.munchies.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    public RestaurantRepository restaurantRepository;


    public List<Restaurant> getAllRest() {
        return restaurantRepository.findAll();

    }

    public Restaurant getOne(Long id) {
        return restaurantRepository.getOne(id);

    }

    public Restaurant saveOne(Restaurant restaurant) {
        return restaurantRepository.saveAndFlush(restaurant);

    }

    public void deleteRestById(Long id) {
        restaurantRepository.deleteById(id);

    }

    public void editOne(Restaurant restaurant) {
        Restaurant restaurant1 = restaurantRepository.getOne(restaurant.getRestaurant_id());
        restaurant1.setRestaurant_id(restaurant.getRestaurant_id());
        restaurant1.setName(restaurant.getName());
        restaurant1.setAddress(restaurant.getAddress());
        restaurant1.setPhoneNumber(restaurant.getPhoneNumber());
        restaurant1.setMenuURL(restaurant.getMenuURL());
        restaurant1.setDeliveryInfo(restaurant.getDeliveryInfo());
        restaurant1.setDeliveryTime(restaurant.getDeliveryTime());
        restaurant1.setAdditionalCharges(restaurant.getAdditionalCharges());
        restaurantRepository.save(restaurant1);


    }
}

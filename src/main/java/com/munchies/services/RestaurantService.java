package com.munchies.services;

import com.munchies.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RestaurantService {

    List<Restaurant> getAllRest();
    Restaurant getOne(Long id);

    Restaurant saveOne(Restaurant restaurant);

    void deleteRestById(Long id);

    void editOne(Restaurant restaurant);

}

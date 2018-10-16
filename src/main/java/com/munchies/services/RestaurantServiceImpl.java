package com.munchies.services;

import com.munchies.model.Restaurant;
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

    public Optional<Restaurant> getOne(Long id) {
        return restaurantRepository.findById(id);

    }
}

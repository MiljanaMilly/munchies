package com.munchies.services;

import com.munchies.model.Restaurant;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RestaurantService {

    List<Restaurant> getAllRest();

    Optional<Restaurant> getOne(Long id);

    Restaurant saveOne(Restaurant restaurant);

    void deleteRestById(Long id) throws NotFoundException;

    void editOne(Restaurant restaurant);

}

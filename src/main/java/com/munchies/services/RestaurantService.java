package com.munchies.services;

import com.munchies.dto.RestaurantDto;
import com.munchies.exceptions.RestaurantExistsException;
import com.munchies.exceptions.RestaurantHasActiveOrdersException;
import com.munchies.model.Restaurant;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RestaurantService {

    List<Restaurant> getAllRest();

    List<RestaurantDto> getAllRestListDto();

    Optional<Restaurant> getOne(Long id);

    RestaurantDto saveOne(RestaurantDto restaurant) throws RestaurantExistsException;

    void deleteRestById(Long id) throws NotFoundException, RestaurantHasActiveOrdersException;

    void editOne(Restaurant restaurant);

}

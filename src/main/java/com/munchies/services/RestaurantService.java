package com.munchies.services;

import com.munchies.dto.RestaurantDto;
import com.munchies.exceptions.RestaurantExistsException;
import com.munchies.exceptions.RestaurantHasActiveOrdersException;
import com.munchies.model.Restaurant;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

@Service
public interface RestaurantService {

    List<RestaurantDto> getAllRestListDto();

    RestaurantDto getOneRestDtoNoOrdersMapped(Long id);

    RestaurantDto saveOne(RestaurantDto restaurant) throws RestaurantExistsException;

    void deleteRestById(Long id) throws NotFoundException, RestaurantHasActiveOrdersException;

    Restaurant editOne(RestaurantDto restaurant);


}

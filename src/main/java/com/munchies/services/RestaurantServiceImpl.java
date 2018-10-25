package com.munchies.services;

import com.munchies.dto.RestaurantDto;
import com.munchies.exceptions.RestaurantExistsException;
import com.munchies.exceptions.RestaurantHasActiveOrdersException;
import com.munchies.model.Order;
import com.munchies.model.Restaurant;
import com.munchies.repositories.OrderJpaRepository;
import com.munchies.repositories.OrderItemJpaRepository;
import com.munchies.repositories.RestaurantJpaRepository;
import com.munchies.services.dtoMappers.RestaurantMapper;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantJpaRepository restaurantJpaRepository;

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    @Autowired
    private OrderItemJpaRepository orderItemJpaRepository;

    @Autowired
    private RestaurantMapper restaurantMapper;


    public List<Restaurant> getAllRest() {
        return restaurantJpaRepository.findAll();
    }

    public List<RestaurantDto> getAllRestListDto() {

        List<RestaurantDto> restListDto = new ArrayList<>();
        for (Restaurant r : restaurantJpaRepository.findAll()) {
            restListDto.add(restaurantMapper.mapEntityToRestDto(r));
        }
        return restListDto;
    }

    public Optional<Restaurant> getOne(Long id) {
        return restaurantJpaRepository.findById(id);
    }

    public RestaurantDto saveOne(RestaurantDto restaurant) throws RestaurantExistsException {

        Restaurant r = new RestaurantMapper().mapRestDtoToEntity(restaurant);
        if (!restaurantJpaRepository.findByNameLike(r.getName()).isPresent()) {
            return restaurantMapper.mapEntityToRestDto(restaurantJpaRepository.save(r));
        } else {
            throw new RestaurantExistsException("Restaurant already exists!!! ");
        }
    }

    @Transactional
    public void deleteRestById(Long id) throws NotFoundException, RestaurantHasActiveOrdersException {

        Optional<Restaurant> r = restaurantJpaRepository.findById(id);
        if (r.isPresent()) {
            for (Order order : r.get().getOrders()) {
                LocalDateTime dateTime = LocalDateTime.now();
                if (dateTime.isAfter(convertToLocalDateTime(order.getOrderTimeout()))) {
                    System.out.println(dateTime);
                    System.out.println(convertToLocalDateTime(order.getOrderTimeout()));
                    orderJpaRepository.delete(order);
                } else {
                    throw new RestaurantHasActiveOrdersException("Restaurant has active orders!!!");
                }
            }
            restaurantJpaRepository.delete(r.get());
        } else {
            throw new NotFoundException("Object with given id doesn't exist!");
        }

    }

    static LocalDateTime convertToLocalDateTime(Date dateToConvert) {

        return new java.sql.Timestamp(
                dateToConvert.getTime()).toLocalDateTime();
    }

    public void editOne(Restaurant restaurant) {

        Restaurant restaurant1 = restaurantJpaRepository.getOne(restaurant.getId());
        //restaurant1.setId(restaurant.getId());
        restaurant1.setName(restaurant.getName());
        restaurant1.setAddress(restaurant.getAddress());
        restaurant1.setPhoneNumber(restaurant.getPhoneNumber());
        restaurant1.setMenuUrl(restaurant.getMenuUrl());
        restaurant1.setDeliveryInfo(restaurant.getDeliveryInfo());
        restaurant1.setDeliveryTime(restaurant.getDeliveryTime());
        restaurant1.setAdditionalCharges(restaurant.getAdditionalCharges());
        restaurantJpaRepository.save(restaurant);

    }

}

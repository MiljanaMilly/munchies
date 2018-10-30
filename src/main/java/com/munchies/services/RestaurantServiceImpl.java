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

    //homePage
    public List<RestaurantDto> getAllRestListDto() {
        List<RestaurantDto> restListDto = new ArrayList<>();
        for (Restaurant r : restaurantJpaRepository.findAll()) {
            restListDto.add(restaurantMapper.mapEntityToRestDtoNoOrdersNoRest(r));
        }
        return restListDto;
    }

    public RestaurantDto getOneRestDtoNoOrdersMapped(Long id) {
        Optional<Restaurant> restaurant = restaurantJpaRepository.findById(id);
        RestaurantDto restaurantDto = new RestaurantDto();
        if (restaurant.isPresent()) {
            restaurantDto = new RestaurantMapper().mapEntityToRestDtoNoOrdersNoRest(restaurant.get());
        }
        return restaurantDto;
    }

    public RestaurantDto getOneRestaurantDto(Long id) {
        Optional<Restaurant> restaurant = restaurantJpaRepository.findById(id);
        RestaurantDto restaurantDto = new RestaurantDto();
        if (restaurant.isPresent()) {
            restaurantDto = new RestaurantMapper().mapEntityToDtoWithRest(restaurant.get());
        }
        return restaurantDto;
    }

    public Optional<Restaurant> getOne(Long id) {
        return restaurantJpaRepository.findById(id);
    }

    public RestaurantDto saveOne(RestaurantDto restaurant) throws RestaurantExistsException {

        Restaurant r = new RestaurantMapper().mapRestDtoToEntityWithOrders(restaurant);
        if (!restaurantJpaRepository.findByNameLike(r.getName()).isPresent()) {
            return restaurantMapper.mapEntityToRestDtoNoOrdersNoRest(restaurantJpaRepository.save(r));
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

    public Restaurant editOne(RestaurantDto restaurant) {
        Restaurant r = new RestaurantMapper().mapRestDtoToEntityWithOrders(restaurant);
        Restaurant restaurant1 = restaurantJpaRepository.getOne(restaurant.getId());
        //restaurant1.setId(r.getId());
        restaurant1.setName(r.getName());
        restaurant1.setAddress(r.getAddress());
        restaurant1.setPhoneNumber(r.getPhoneNumber());
        restaurant1.setMenuUrl(r.getMenuUrl());
        restaurant1.setDeliveryInfo(r.getDeliveryInfo());
        restaurant1.setDeliveryTime(r.getDeliveryTime());
        restaurant1.setAdditionalCharges(r.getAdditionalCharges());
        return restaurantJpaRepository.save(r);

    }

}

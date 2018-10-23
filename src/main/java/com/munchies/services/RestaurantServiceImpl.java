package com.munchies.services;

import com.munchies.model.Order;
import com.munchies.model.OrderItem;
import com.munchies.model.Restaurant;
import com.munchies.repositories.OrderJpaRepository;
import com.munchies.repositories.OrderItemJpaRepository;
import com.munchies.repositories.RestaurantJpaRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
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


    public List<Restaurant> getAllRest() {
        return restaurantJpaRepository.findAll();

    }

    public Optional<Restaurant> getOne(Long id) {
        return restaurantJpaRepository.findById(id);

    }

    public Restaurant saveOne(Restaurant restaurant) {
        return restaurantJpaRepository.save(restaurant);

    }

    @Transactional
    public void deleteRestById(Long id) throws NotFoundException {

        Optional<Restaurant> r = restaurantJpaRepository.findById(id);
        if (r.isPresent()) {
            for (Order order : r.get().getOrders()) {
                //if not active
                restaurantJpaRepository.delete(r.get());


            }

            List<Order> orders = orderJpaRepository.findGroupOrdersByRestaurant(r.get());

            for (Order go : orders) {
                List<OrderItem> lo = orderItemJpaRepository.findOrdersByGroupOrder(orderJpaRepository.getOne(go.getId()));
                if (!lo.isEmpty()) {
                    for (OrderItem o : lo) {
                        orderItemJpaRepository.delete(o);
                    }
                }
                orderJpaRepository.delete(go);

            }
            restaurantJpaRepository.delete(r.get());
        } else {
            throw new NotFoundException("Object with given id doesn't exist!");
        }

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

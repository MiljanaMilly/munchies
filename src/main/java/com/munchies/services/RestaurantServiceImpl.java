package com.munchies.services;

import com.munchies.model.GroupOrder;
import com.munchies.model.Order;
import com.munchies.model.Restaurant;
import com.munchies.repositories.GroupOrderRepository;
import com.munchies.repositories.OrderRepository;
import com.munchies.repositories.RestaurantRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private GroupOrderRepository groupOrderRepository;

    @Autowired
    private OrderRepository orderRepository;


    public List<Restaurant> getAllRest() {
        return restaurantRepository.findAll();

    }

    public Restaurant getOne(Long id) {
        return restaurantRepository.getOne(id);

    }

    public Restaurant saveOne(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);

    }

    @Transactional
    public void deleteRestById(Long id) throws NotFoundException {

        Optional<Restaurant> r = restaurantRepository.findById(id);
        if (r.isPresent()) {
            for (GroupOrder order : r.get().getGroupOrder()) {
                //if not active
                restaurantRepository.delete(r.get());


            }

            List<GroupOrder> groupOrders = groupOrderRepository.findGroupOrdersByRestaurant(r.get());

            for (GroupOrder go : groupOrders) {
                List<Order> lo = orderRepository.findOrdersByGroupOrder(groupOrderRepository.getOne(go.getGroup_order_id()));
                if (!lo.isEmpty()) {
                    for (Order o : lo) {
                        orderRepository.delete(o);
                    }
                }
                groupOrderRepository.delete(go);

            }
            restaurantRepository.delete(r.get());
        } else {
            throw new NotFoundException("Object with given id doesn't exist!");
        }

    }

    public void editOne(Restaurant restaurant) {
        Restaurant restaurant1 = new Restaurant();
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

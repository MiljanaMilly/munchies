package com.munchies.services;

import com.munchies.model.Order;
import com.munchies.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order saveOne(Order order){
        return orderRepository.save(order);
    }
}

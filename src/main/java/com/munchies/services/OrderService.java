package com.munchies.services;

import com.munchies.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    Order save(Order order);

    Order findOne(Long id);

    List<Order> getActiveOrders();
}


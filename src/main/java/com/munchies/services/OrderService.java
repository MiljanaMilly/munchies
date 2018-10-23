package com.munchies.services;

import com.munchies.model.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    Order save(Order order);

    Order findOne(Long id);
}


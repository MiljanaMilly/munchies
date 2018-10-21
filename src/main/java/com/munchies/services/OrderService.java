package com.munchies.services;

import com.munchies.model.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    public Order saveOne(Order order);
}

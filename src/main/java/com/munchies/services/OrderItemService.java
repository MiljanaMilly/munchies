package com.munchies.services;

import com.munchies.model.OrderItem;
import org.springframework.stereotype.Service;

@Service
public interface OrderItemService {

    OrderItem saveOne(OrderItem orderItem);
}

package com.munchies.services;

import com.munchies.dto.OrderItemDto;
import com.munchies.exceptions.OrderIsNotActiveException;
import com.munchies.model.OrderItem;
import org.springframework.stereotype.Service;

@Service
public interface OrderItemService {

    OrderItemDto saveOne(OrderItemDto orderItem) throws OrderIsNotActiveException;
}

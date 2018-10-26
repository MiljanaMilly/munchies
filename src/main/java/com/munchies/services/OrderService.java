package com.munchies.services;

import com.munchies.dto.OrderDto;
import com.munchies.dto.OrderItemDto;
import com.munchies.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    OrderDto save(OrderDto order);

    OrderDto findOne(Long id);

    List<Order> getActiveOrders();

    OrderDto findOneOrderDto(Long id);

    OrderDto findOneOrder(Long id);

    List<OrderItemDto> findListOfItemsbyOrderId(Long id);
}


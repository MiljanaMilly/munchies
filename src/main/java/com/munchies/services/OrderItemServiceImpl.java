package com.munchies.services;

import com.munchies.model.OrderItem;
import com.munchies.repositories.OrderItemJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemJpaRepository orderItemJpaRepository;

    public OrderItem saveOne(OrderItem orderItem) {
        return orderItemJpaRepository.save(orderItem);
    }
}

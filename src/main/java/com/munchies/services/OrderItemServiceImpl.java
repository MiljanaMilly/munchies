package com.munchies.services;

import com.munchies.exceptions.OrderIsNotActiveException;
import com.munchies.model.OrderItem;
import com.munchies.repositories.OrderItemJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemJpaRepository orderItemJpaRepository;

    public OrderItem saveOne(OrderItem orderItem) throws OrderIsNotActiveException {
        Date orderTimeout = orderItem.getOrder().getOrderTimeout();
        LocalDateTime dateTime = LocalDateTime.now();
        if (dateTime.isBefore(convertToLocalDateTime(orderTimeout))) {
            return orderItemJpaRepository.save(orderItem);
        } else {
            throw new OrderIsNotActiveException("Order is expired");
        }
    }

    static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return new java.sql.Timestamp(
                dateToConvert.getTime()).toLocalDateTime();
    }

}

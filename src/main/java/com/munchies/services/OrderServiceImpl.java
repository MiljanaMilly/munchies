package com.munchies.services;


import com.munchies.model.Order;
import com.munchies.repositories.OrderJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    public Order save(Order order) {
        LocalDateTime dateTime = LocalDateTime.now().plus(Duration.of(10, ChronoUnit.MINUTES));
        Date tmfn = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        order.setOrderTimeout(tmfn);
        return orderJpaRepository.saveAndFlush(order);

    }

    public Order findOne(Long id) {
        return orderJpaRepository.getOne(id);

    }

    public List<Order> getActiveOrders() {
        LocalDateTime dateTime = LocalDateTime.now();
        Date timeNow = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        return orderJpaRepository.findByOrderTimeoutIsAfter(timeNow);

    }



}

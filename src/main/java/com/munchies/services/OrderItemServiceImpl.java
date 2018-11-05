package com.munchies.services;

import com.munchies.dto.OrderDto;
import com.munchies.dto.OrderItemDto;
import com.munchies.exceptions.OrderIsNotActiveException;
import com.munchies.exceptions.OrderNotExistsException;
import com.munchies.model.Order;
import com.munchies.model.OrderItem;
import com.munchies.repositories.OrderItemJpaRepository;
import com.munchies.repositories.OrderJpaRepository;
import com.munchies.services.dtoMappers.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.munchies.services.dtoMappers.OrderMapper;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemJpaRepository orderItemJpaRepository;
    @Autowired
    private OrderJpaRepository orderJpaRepository;
    @Autowired
    private OrderItemMapper orderItemMapper;


    public OrderItemDto saveOne(OrderItemDto orderItemDot) throws OrderIsNotActiveException, OrderNotExistsException {
        Optional<Order> order = orderJpaRepository.findById(orderItemDot.getOrderId());
        OrderItemDto savedDto = new OrderItemDto();
        if (order.isPresent()) {
            Date orderTimeout = order.get().getOrderTimeout();
            LocalDateTime dateTime = LocalDateTime.now();
            if (dateTime.isBefore(convertToLocalDateTime(orderTimeout))) {
                OrderItem orderItem = orderItemMapper.mapOrderItemDtoToEntity(orderItemDot);
                orderItem.setOrder(order.get());
                OrderItem saved = orderItemJpaRepository.save(orderItem);
                savedDto = orderItemMapper.mapEntityToOrderItemDto(saved);

            } else {
                throw new OrderIsNotActiveException("Order is expired");
            }
        } else {
            throw new OrderNotExistsException("Order doesn't exist!");
        }
        return savedDto;
    }

    static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return new java.sql.Timestamp(
                dateToConvert.getTime()).toLocalDateTime();
    }
    public OrderItemDto findOne(Long id){
        OrderItem orderItem = (orderItemJpaRepository.findById(id)).get();
        return new OrderItemMapper().mapEntityToOrderItemDto(orderItem);

    }

}

package com.munchies.services;

import com.munchies.dto.OrderDto;
import com.munchies.dto.OrderItemDto;
import com.munchies.exceptions.OrderIsNotActiveException;
import com.munchies.model.Order;
import com.munchies.model.OrderItem;
import com.munchies.repositories.OrderItemJpaRepository;
import com.munchies.services.dtoMappers.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.munchies.services.dtoMappers.OrderMapper;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemJpaRepository orderItemJpaRepository;

    public OrderItemDto saveOne(OrderItemDto orderItem) throws OrderIsNotActiveException {
        Date orderTimeout = orderItem.getOrder().getOrderTimeout();
        LocalDateTime dateTime = LocalDateTime.now();
        if (dateTime.isBefore(convertToLocalDateTime(orderTimeout))) {
            OrderItem saveOrderItem = new OrderItemMapper().mapOrderItemDtoToEntity(orderItem);
            Order o = new OrderMapper().mapDtoToEntity(orderItem.getOrder());
            OrderItem orderItem1 = orderItemJpaRepository.save(saveOrderItem);
            orderItem1.setOrder(o);
            return new OrderItemMapper().mapEntityToOrderItemDto(orderItemJpaRepository.save(orderItem1));
        } else {
            throw new OrderIsNotActiveException("Order is expired");
        }
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

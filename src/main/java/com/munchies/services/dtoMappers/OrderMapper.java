package com.munchies.services.dtoMappers;


import com.munchies.dto.OrderDto;
import com.munchies.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order mapOrderDtoToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCreator(orderDto.getCreator());
        order.setOrderTimeout(orderDto.getOrderTimeout());
        order.setOrderUrl(orderDto.getOrderUrl());
        return order;
    }

    public OrderDto mapEntityToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCreator(order.getCreator());
        orderDto.setOrderTimeout(order.getOrderTimeout());
        orderDto.setOrderUrl(order.getOrderUrl());
        return orderDto;
    }
}

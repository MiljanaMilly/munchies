package com.munchies.services.dtoMappers;


import com.munchies.dto.OrderDto;
import com.munchies.model.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    public Order mapOrderDtoToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCreator(orderDto.getCreator());
        order.setOrderTimeout(orderDto.getOrderTimeout());
        order.setOrderUrl(orderDto.getOrderUrl());
        order.setOrderItems(new OrderItemMapper().mapDtosToOrderItems(orderDto.getOrderItems()));
        return order;
    }

    public OrderDto mapEntityToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCreator(order.getCreator());
        orderDto.setOrderTimeout(order.getOrderTimeout());
        orderDto.setOrderUrl(order.getOrderUrl());
        orderDto.setOrderItems(new OrderItemMapper().mapEntitiesToDtos(order.getOrderItems()));
        return orderDto;
    }

    public List<Order> mapDtoOrdersToEntities(List<OrderDto> orderDtos) {
        List<Order> orderList = new ArrayList<>();
        for (OrderDto o : orderDtos) {
            orderList.add(new OrderMapper().mapOrderDtoToEntity(o));
        }
        return orderList;
    }

    public List<OrderDto> mapEntitiesToDtoOrders(List<Order> orders) {
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order o : orders) {
            orderDtos.add(new OrderMapper().mapEntityToOrderDto(o));
        }
        return orderDtos;

    }
}

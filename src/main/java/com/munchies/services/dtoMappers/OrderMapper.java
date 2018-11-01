package com.munchies.services.dtoMappers;


import com.munchies.dto.OrderDto;
import com.munchies.dto.OrderItemDto;
import com.munchies.model.Order;
import com.munchies.repositories.RestaurantJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {


    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RestaurantMapper restaurantMapper;


    //crate group order
    public Order mapOrderDtoToEntityWithOrderItems(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCreator(orderDto.getCreator());
        order.setOrderTimeout(orderDto.getOrderTimeout());
        order.setOrderUrl(orderDto.getOrderUrl());
        order.setOrderItems(orderItemMapper.mapDtosToOrderItems(orderDto.getOrderItems()));
        return order;
    }

    //    public Order mapDtoToEntity(OrderDto orderDto) {
//        Order order = new Order();
//        order.setId(orderDto.getId());
//        order.setCreator(orderDto.getCreator());
//        order.setOrderTimeout(orderDto.getOrderTimeout());
//        order.setOrderUrl(orderDto.getOrderUrl());
//        return order;
//    }
//
//    public Order mapDtosToEntityWithItemsWithRest(OrderDto orderDto) {
//        Order order = new Order();
//        order.setId(orderDto.getId());
//        order.setCreator(orderDto.getCreator());
//        order.setOrderTimeout(orderDto.getOrderTimeout());
//        order.setOrderUrl(orderDto.getOrderUrl());
//        order.setOrderItems(orderItemMapper.mapDtosToOrderItems(orderDto.getOrderItems()));
//        order.setRestaurant(restaurantMapper.mapDtosToEntityNoOrdersNoRest(orderDto.getRestaurant()));
//        return order;
//    }
//group order
public OrderDto mapEntityToOrderDtoWithRestWithItems(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCreator(order.getCreator());
        orderDto.setOrderTimeout(order.getOrderTimeout());
        orderDto.setOrderUrl(order.getOrderUrl());
    orderDto.setOrderItems(orderItemMapper.mapEntitiesToDtos(order.getOrderItems()));
    orderDto.setRestaurant(restaurantMapper.mapEntityToRestDtoNoOrdersNoRest(order.getRestaurant()));
        return orderDto;
    }
//restaurant - new order
    public OrderDto mapEntityOrderToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCreator(order.getCreator());
        orderDto.setOrderTimeout(order.getOrderTimeout());
        orderDto.setOrderUrl(order.getOrderUrl());
        orderDto.setRestaurant(restaurantMapper.mapEntityToRestDtoNoOrdersNoRest(order.getRestaurant()));
        return orderDto;
    }

    //new group order
    public OrderDto mapEntityToDtos(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCreator(order.getCreator());
        orderDto.setOrderTimeout(order.getOrderTimeout());
        orderDto.setOrderUrl(order.getOrderUrl());
        List<OrderItemDto> orderItemDtos = orderItemMapper.mapEntitiesToDtos(order.getOrderItems());
        orderDto.setRestaurant(restaurantMapper.mapEntityToRestDtoNoOrdersNoRest(order.getRestaurant()));
        orderDto.setOrderItems(orderItemDtos);
        return orderDto;
    }

    //new group order
    public OrderDto mapEntityToDtooo(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCreator(order.getCreator());
        orderDto.setOrderTimeout(order.getOrderTimeout());
        orderDto.setOrderUrl(order.getOrderUrl());
        List<OrderItemDto> orderItemDtos;
        orderItemDtos = orderItemMapper.mapEntitiesToDtos(order.getOrderItems());
        orderDto.setOrderItems(orderItemDtos);
        return orderDto;
    }

    //active orders
    public OrderDto mapActiveOrderToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCreator(order.getCreator());
        orderDto.setOrderTimeout(order.getOrderTimeout());
        orderDto.setOrderUrl(order.getOrderUrl());
        return orderDto;
    }

    public List<Order> mapDtoOrdersToEntities(List<OrderDto> orderDtos) {
        List<Order> orderList = new ArrayList<>();
        for (OrderDto o : orderDtos) {
            orderList.add(orderMapper.mapOrderDtoToEntityWithOrderItems(o));
        }
        return orderList;
    }

    public List<OrderDto> mapEntitiesToDtoOrders(List<Order> orders) {
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order o : orders) {
            orderDtos.add(orderMapper.mapEntityToOrderDtoWithRestWithItems(o));
        }
        return orderDtos;

    }

    public List<OrderDto> mapEntitiesToDtos(List<Order> orders) {
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order o : orders) {
            orderDtos.add(orderMapper.mapEntityOrderToOrderDto(o));
        }
        return orderDtos;

    }
}

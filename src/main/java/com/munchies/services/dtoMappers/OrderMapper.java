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
    private RestaurantJpaRepository restaurantJpaRepository;
    @Autowired
    private OrderItemMapper orderItemMapper;


//group order
    public Order mapOrderDtoToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCreator(orderDto.getCreator());
        order.setOrderTimeout(orderDto.getOrderTimeout());
        order.setOrderUrl(orderDto.getOrderUrl());
        order.setOrderItems(new OrderItemMapper().mapDtosToOrderItems(orderDto.getOrderItems()));
        return order;
    }

    //new group order-save order item
    //save order item 2
    public Order mapDtoToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCreator(orderDto.getCreator());
        order.setOrderTimeout(orderDto.getOrderTimeout());
        order.setOrderUrl(orderDto.getOrderUrl());
        return order;
    }

    public Order mapDtosToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCreator(orderDto.getCreator());
        order.setOrderTimeout(orderDto.getOrderTimeout());
        order.setOrderUrl(orderDto.getOrderUrl());
        order.setOrderItems(new OrderItemMapper().mapDtosToOrderItems(orderDto.getOrderItems()));
        order.setRestaurant(new RestaurantMapper().mapDtosToEntity(orderDto.getRestaurant()));
        return order;
    }
//group order
    public OrderDto mapEntityToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCreator(order.getCreator());
        orderDto.setOrderTimeout(order.getOrderTimeout());
        orderDto.setOrderUrl(order.getOrderUrl());
        orderDto.setOrderItems(new OrderItemMapper().mapEntitiesToDtos(order.getOrderItems()));
        orderDto.setRestaurant(new RestaurantMapper().mapEntityToRestDto(order.getRestaurant()));
        return orderDto;
    }
//restaurant - new order
    public OrderDto mapEntityOrderToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCreator(order.getCreator());
        orderDto.setOrderTimeout(order.getOrderTimeout());
        orderDto.setOrderUrl(order.getOrderUrl());
        orderDto.setRestaurant(new RestaurantMapper().mapEntityToRestDto(order.getRestaurant()));
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
        orderDto.setRestaurant(new RestaurantMapper().mapEntityToRestDto(order.getRestaurant()));
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
        List<OrderItemDto> orderItemDtos = new ArrayList<>();
        orderItemDtos = orderItemMapper.mapEntitiesToDtos(order.getOrderItems());
        orderDto.setOrderItems(orderItemDtos);
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

    public List<OrderDto> mapEntitiesToDtos(List<Order> orders) {
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order o : orders) {
            orderDtos.add(new OrderMapper().mapEntityToDtos(o));
        }
        return orderDtos;

    }
}

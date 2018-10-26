package com.munchies.services;


import com.munchies.dto.OrderDto;
import com.munchies.dto.OrderItemDto;
import com.munchies.dto.RestaurantDto;
import com.munchies.model.Order;
import com.munchies.model.OrderItem;
import com.munchies.model.Restaurant;
import com.munchies.repositories.OrderJpaRepository;
import com.munchies.services.dtoMappers.OrderItemMapper;
import com.munchies.services.dtoMappers.OrderMapper;
import com.munchies.services.dtoMappers.RestaurantMapper;
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
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    public OrderDto save(OrderDto order) {
        LocalDateTime dateTime = LocalDateTime.now().plus(Duration.of(10, ChronoUnit.MINUTES));
        Date tmfn = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        order.setOrderTimeout(tmfn);
        Order saveOrder = new OrderMapper().mapOrderDtoToEntity(order);
        Restaurant r = new RestaurantMapper().mapDtosToEntity(order.getRestaurant());
        //saveOrder.setRestaurant(r);
        Order o = orderJpaRepository.save(saveOrder);
        o.setRestaurant(r);
        return new OrderMapper().mapEntityToOrderDto(orderJpaRepository.save(o));
    }
//new group order
    public OrderDto findOne(Long id) {
        Order o = orderJpaRepository.getOne(id);
        return new OrderMapper().mapEntityToDtooo(o);
    }
    //find group order- new group order
    public OrderDto findOneOrder(Long id) {
        Order o = orderJpaRepository.getOne(id);
        OrderDto orderDto = orderMapper.mapEntityToDtos(o);
        return orderDto;
    }

    public OrderDto findOneOrderDto(Long id) {
        Order o = orderJpaRepository.getOne(id);
        return new OrderMapper().mapEntityToDtooo(o);
    }

    public List<Order> getActiveOrders() {
        LocalDateTime dateTime = LocalDateTime.now();
        Date timeNow = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        return orderJpaRepository.findByOrderTimeoutIsAfter(timeNow);

    }

    public List<OrderItemDto> findListOfItemsbyOrderId(Long id) {
        Order order = orderJpaRepository.getOne(id);
        List<OrderItem> itemList = order.getOrderItems();
        List<OrderItemDto> itemDtoList = orderItemMapper.mapEntitiesToDtos(itemList);
        return itemDtoList;


    }



}

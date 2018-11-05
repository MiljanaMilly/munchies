package com.munchies.services;


import com.munchies.dto.OrderDto;
import com.munchies.dto.OrderItemDto;
import com.munchies.model.Order;
import com.munchies.model.OrderItem;
import com.munchies.model.Restaurant;
import com.munchies.repositories.OrderJpaRepository;
import com.munchies.services.dtoMappers.OrderItemMapper;
import com.munchies.services.dtoMappers.OrderMapper;
import com.munchies.services.dtoMappers.RestaurantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Autowired
    private EmailService emailService;

    public OrderDto save(OrderDto order) {
        LocalDateTime dateTime = LocalDateTime.now().plus(Duration.of(10, ChronoUnit.MINUTES));
        Date tmfn = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

        order.setOrderTimeout(tmfn);
        Order saveOrder = orderMapper.mapOrderDtoToEntityWithOrderItems(order);
        Restaurant r = restaurantMapper.mapDtosToEntityNoOrdersNoRest(order.getRestaurant());
        Order o = orderJpaRepository.save(saveOrder);
        o.setRestaurant(r);
        o.setSentEmail(0);
        return orderMapper.mapEntityToOrderDtoWithRestWithItems(orderJpaRepository.save(o));
    }

    //new group order
    public OrderDto findOneOrderDtoWithOrderItems(Long id) {
        Order o = orderJpaRepository.getOne(id);
        return new OrderMapper().mapEntityToDtooo(o);
    }

    //find group order- new group order
    public OrderDto findOneOrderDtoWithRestWithItems(Long id) {
        Order o = orderJpaRepository.getOne(id);
        OrderDto orderDto = orderMapper.mapEntityToDtos(o);
        return orderDto;
    }

    public OrderDto findOneOrderDto(Long id) {
        Order o = orderJpaRepository.getOne(id);
        return new OrderMapper().mapEntityToDtooo(o);
    }

    public List<OrderDto> getActiveOrders() {
        LocalDateTime dateTime = LocalDateTime.now();
        Date timeNow = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        List<Order> orderList = orderJpaRepository.findByOrderTimeoutIsAfter(timeNow);
        List<OrderDto> orderDtoList = orderMapper.mapEntitiesToDtos(orderList);
        return orderDtoList;

    }

    public List<OrderItemDto> findListOfItemsbyOrderId(Long id) {
        Order order = orderJpaRepository.getOne(id);
        List<OrderItem> itemList = order.getOrderItems();
        List<OrderItemDto> itemDtoList = orderItemMapper.mapEntitiesToDtos(itemList);
        return itemDtoList;


    }

    public void sendOrdersEmail(Long id) throws MessagingException {
        Optional<Order> groupOrder = orderJpaRepository.findById(id);
        if (groupOrder.isPresent()) {
            Order grOrder = groupOrder.get();
            emailService.sendEmail(grOrder);
        }
    }


}

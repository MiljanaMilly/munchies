package com.munchies.services;

import com.munchies.dto.OrderDto;
import com.munchies.dto.OrderItemDto;
import com.munchies.model.Order;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public interface OrderService {

    OrderDto save(OrderDto order);

    List<OrderDto> getActiveOrders();

    OrderDto findOneOrderDtoWithRestWithItems(Long id);

    List<OrderItemDto> findListOfItemsbyOrderId(Long id);

    public void sendOrdersEmail(Long id) throws MessagingException;
}


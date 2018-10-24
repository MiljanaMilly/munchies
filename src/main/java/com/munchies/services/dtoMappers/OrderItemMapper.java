package com.munchies.services.dtoMappers;

import com.munchies.dto.OrderItemDto;
import com.munchies.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItemDto mapEntityToOrderItemDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setName(orderItem.getName());
        orderItemDto.setCreator(orderItem.getCreator());
        orderItemDto.setPrice(orderItem.getPrice());
        return orderItemDto;
    }

    public OrderItem mapOrderItemDtoToEntity(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDto.getId());
        orderItem.setName(orderItemDto.getName());
        orderItem.setCreator(orderItemDto.getCreator());
        orderItem.setPrice(orderItemDto.getPrice());
        return orderItem;
    }
}

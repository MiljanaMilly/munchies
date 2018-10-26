package com.munchies.services.dtoMappers;

import com.munchies.dto.OrderItemDto;
import com.munchies.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        //orderItem.setId(orderItemDto.getId());
        orderItem.setName(orderItemDto.getName());
        orderItem.setCreator(orderItemDto.getCreator());
        orderItem.setPrice(orderItemDto.getPrice());
        return orderItem;
    }
    //save new order item 1
    /*public OrderItem mapItemDtoToEntity(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDto.getId());
        orderItem.setName(orderItemDto.getName());
        orderItem.setCreator(orderItemDto.getCreator());
        orderItem.setPrice(orderItemDto.getPrice());
        orderItem.setOrder(new OrderMapper().mapDtoToEntity(orderItemDto.getOrder()));
        return orderItem;
    }*/

    public List<OrderItem> mapDtosToOrderItems(List<OrderItemDto> orderItemDtos) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDto orderItemDto : orderItemDtos) {
            orderItems.add(new OrderItemMapper().mapOrderItemDtoToEntity(orderItemDto));
        }
        return orderItems;
    }

    public List<OrderItemDto> mapEntitiesToDtos(List<OrderItem> orderItems) {
        List<OrderItemDto> orderItemDtos = new ArrayList<>();
        if (!orderItems.isEmpty()) {
            for (OrderItem orderItem : orderItems) {
                orderItemDtos.add(new OrderItemMapper().mapEntityToOrderItemDto(orderItem));
            }
        }
        return orderItemDtos;
    }
}

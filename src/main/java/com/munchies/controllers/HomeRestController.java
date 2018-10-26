package com.munchies.controllers;

import com.munchies.dto.OrderDto;
import com.munchies.dto.OrderItemDto;
import com.munchies.model.Order;
import com.munchies.model.OrderItem;
import com.munchies.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeRestController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/getAjaxData/{id}", method = RequestMethod.GET)
    public List<OrderItemDto> getAllOrders(@PathVariable Long id) {
        List<OrderItemDto> orderItems = orderService.findListOfItemsbyOrderId(id);
        return orderItems;


    }


}

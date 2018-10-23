package com.munchies.controllers;

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
    public List<OrderItem> getAllOrders(@PathVariable Long id) {
        Order order = orderService.findOne(id);
        List<OrderItem> orderItems = order.getOrderItems();
//        if(orderItems.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return orderItems;


    }


}

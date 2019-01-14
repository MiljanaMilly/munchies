package com.munchies.controllers;

import com.munchies.dto.OrderDto;
import com.munchies.dto.OrderItemDto;
import com.munchies.model.Order;
import com.munchies.model.OrderItem;
import com.munchies.model.Restaurant;
import com.munchies.services.OrderService;
import com.munchies.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeRestController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value = "/getAjaxData/{id}", method = RequestMethod.GET)
    public List<OrderItemDto> getAllOrders(@PathVariable Long id) {
        List<OrderItemDto> orderItems = orderService.findListOfItemsbyOrderId(id);
        return orderItems;
    }

    @RequestMapping(value = "/sendEmail/{id}", method = RequestMethod.GET)
    public void sendEmail(@PathVariable Long id) throws MessagingException {
        orderService.sendOrdersEmail(id);
    }

    @RequestMapping(value = "/getGroupOrders", method = RequestMethod.GET)
    public List<OrderDto> getOrders() {
        List<OrderDto> list = orderService.getActiveOrders();
        return list;
    }

//    @RequestMapping(value = "/sortandpage", method = RequestMethod.GET)
//    public ResponseEntity<?> getOrders(Pageable pageable) {
//        Page<Restaurant> restaurants = restaurantService.findAllPagingAndSorting(pageable);
//        return new ResponseEntity(restaurants, HttpStatus.OK);
//    }


}

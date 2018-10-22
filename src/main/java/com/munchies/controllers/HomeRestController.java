package com.munchies.controllers;

import com.munchies.model.GroupOrder;
import com.munchies.model.Order;
import com.munchies.model.User;
import com.munchies.services.GroupOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeRestController {

    @Autowired
    private GroupOrderService groupOrderService;

    @RequestMapping(value = "/getAjaxData/{id}", method = RequestMethod.GET)
    public List<Order> getAllOrders(@PathVariable Long id) {
        GroupOrder groupOrder = groupOrderService.findOne(id);
        List<Order> orders = groupOrder.getOrder();
//        if(orders.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return orders;


    }


}

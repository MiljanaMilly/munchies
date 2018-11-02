package com.munchies.services;

import com.munchies.model.Order;
import com.munchies.repositories.OrderJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasksService {

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    @Autowired
    private OrderService orderService;

//    @Scheduled(fixedRate = 5000)
//    public void sendScheduledEmail() throws MessagingException {
//
//        LocalDateTime dateTime = LocalDateTime.now();
//        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
//        Date date = Date.from(instant);
//        List<Order> orderList = orderJpaRepository.findOrdersByOrderTimeoutBefore(date);
//        if(!orderList.isEmpty()){
//            for(Order r: orderList){
//                orderService.sendOrdersEmail(r.getId());
//            }
//
//    }
//
//
//    }


}

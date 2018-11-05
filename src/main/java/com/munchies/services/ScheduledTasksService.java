package com.munchies.services;

import com.munchies.model.Order;
import com.munchies.repositories.OrderJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.time.*;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasksService {

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    @Autowired
    private OrderService orderService;

    @Scheduled(fixedRate = 5000, initialDelay = 10000)
    public void sendScheduledEmail() throws MessagingException {
        LocalDateTime dateTime = LocalDateTime.now();
        Date date = convertToDate(dateTime);
        System.out.println("Scheduled task - 5sec");
        List<Order> orderList = orderJpaRepository.findOrdersBySentEmailIsLikeAndOrderTimeoutBefore(0, date);
        if (!orderList.isEmpty()) {
            for (Order r : orderList) {
                orderService.sendOrdersEmail(r.getId());
                r.setSentEmail(1);
                orderJpaRepository.save(r);
            }
        }


    }

    public Date convertToDate(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }


}

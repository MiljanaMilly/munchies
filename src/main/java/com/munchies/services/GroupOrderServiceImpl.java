package com.munchies.services;


import com.munchies.model.GroupOrder;
import com.munchies.repositories.GroupOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class GroupOrderServiceImpl implements GroupOrderService {

    @Autowired
    private GroupOrderRepository groupOrderRepository;

    public GroupOrder save(GroupOrder groupOrder) {
        LocalDateTime dateTime = LocalDateTime.now().plus(Duration.of(10, ChronoUnit.MINUTES));
        Date tmfn = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        groupOrder.setOrder_timeout(tmfn);
        return groupOrderRepository.saveAndFlush(groupOrder);

    }

    public GroupOrder findOne(Long id) {
        return groupOrderRepository.getOne(id);

    }

}

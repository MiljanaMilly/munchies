package com.munchies.services;


import com.munchies.model.GroupOrder;
import com.munchies.repositories.GroupOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupOrderServiceImpl implements GroupOrderService {

    @Autowired
    private GroupOrderRepository groupOrderRepository;

    public GroupOrder save(GroupOrder groupOrder) {
        return groupOrderRepository.saveAndFlush(groupOrder);

    }

}

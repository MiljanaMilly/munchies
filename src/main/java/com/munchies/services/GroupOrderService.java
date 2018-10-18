package com.munchies.services;

import com.munchies.model.GroupOrder;
import org.springframework.stereotype.Service;

@Service
public interface GroupOrderService {

    public GroupOrder save(GroupOrder groupOrder);
}

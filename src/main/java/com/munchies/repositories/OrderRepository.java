package com.munchies.repositories;

import com.munchies.model.GroupOrder;
import com.munchies.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("from Order o where o.groupOrder like :grouporder")
    List<Order> findOrdersByGroupOrder(@Param("grouporder") GroupOrder groupOrder);
}

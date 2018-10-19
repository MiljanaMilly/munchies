package com.munchies.repositories;

import com.munchies.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("select order_id from orders o where o.group_order_id like :id")
    List<Order> findOrdersByGroupOrderId(@Param("id") Long id);
}

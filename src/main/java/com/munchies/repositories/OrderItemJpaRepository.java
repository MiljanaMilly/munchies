package com.munchies.repositories;

import com.munchies.model.Order;
import com.munchies.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource
public interface OrderItemJpaRepository extends JpaRepository<OrderItem, Long> {

    @Query("from OrderItem o where o.order like :order")
    List<OrderItem> findOrdersByGroupOrder(@Param("order") Order order);
}

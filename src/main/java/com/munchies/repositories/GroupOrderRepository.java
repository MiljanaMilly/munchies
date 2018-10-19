package com.munchies.repositories;

import com.munchies.model.GroupOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupOrderRepository extends JpaRepository<GroupOrder,Long> {


    @Query("select group_order_id from group_order go where go.restaurant_id like :id")
    List<GroupOrder> findGroupOrdersByRestaurantId(@Param("id") Long id);
}

package com.munchies.repositories;

import com.munchies.model.GroupOrder;
import com.munchies.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupOrderRepository extends JpaRepository<GroupOrder,Long> {


    @Query("select group_order_id from GroupOrder go where go.restaurant like :restaurant")
    List<GroupOrder> findGroupOrdersByRestaurant(@Param("restaurant") Restaurant restaurant);
}

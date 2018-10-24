package com.munchies.repositories;

import com.munchies.model.Order;
import com.munchies.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@RepositoryRestResource
public interface OrderJpaRepository extends JpaRepository<Order, Long> {

    @Query("From Order go where go.restaurant like :restaurant")
    List<Order> findGroupOrdersByRestaurant(@Param("restaurant") Restaurant restaurant);

    @Override
    Page<Order> findAll(Pageable pageable);

    List<Order> findByOrderTimeoutIsAfter(Date date);
}

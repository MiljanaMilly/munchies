package com.munchies.repositories;

import com.munchies.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource
public interface RestaurantJpaRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findByNameLike(String name);
}

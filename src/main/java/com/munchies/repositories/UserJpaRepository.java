package com.munchies.repositories;

import com.munchies.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


@Repository
@RepositoryRestResource
public interface UserJpaRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}

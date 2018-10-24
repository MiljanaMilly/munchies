package com.munchies.repositories;

import com.munchies.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface RoleJpaRepository extends JpaRepository<Role, Long> {
}

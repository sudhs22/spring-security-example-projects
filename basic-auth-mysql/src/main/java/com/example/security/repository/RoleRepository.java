package com.example.security.repository;

import com.example.security.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}

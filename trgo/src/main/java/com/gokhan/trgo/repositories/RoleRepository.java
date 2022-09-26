package com.gokhan.trgo.repositories;

import com.gokhan.trgo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
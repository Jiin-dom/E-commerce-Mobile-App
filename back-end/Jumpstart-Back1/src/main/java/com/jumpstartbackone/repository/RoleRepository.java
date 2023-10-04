package com.jumpstartbackone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jumpstartbackone.entity.Role;
import com.jumpstartbackone.entity.RoleName;


public interface RoleRepository extends JpaRepository<Role, Long> {

     Role findByName(String name);
	 Optional<Role> findByName(RoleName roleName);
}


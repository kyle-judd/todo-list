package com.workhardkj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workhardkj.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>, RoleRepositoryCustom {
	
}

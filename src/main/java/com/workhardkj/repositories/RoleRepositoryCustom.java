package com.workhardkj.repositories;

import java.util.Optional;

import com.workhardkj.entity.Role;

public interface RoleRepositoryCustom {
	Optional<Role> findRoleByType(String roleType);
}

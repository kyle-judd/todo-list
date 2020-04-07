package com.workhardkj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workhardkj.entity.User;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
	
}

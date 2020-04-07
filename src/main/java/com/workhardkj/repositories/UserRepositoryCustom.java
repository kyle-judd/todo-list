package com.workhardkj.repositories;

import com.workhardkj.entity.User;

public interface UserRepositoryCustom {
	User findUserByUsername(String username);
}

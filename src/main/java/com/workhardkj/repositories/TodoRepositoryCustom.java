package com.workhardkj.repositories;

import java.util.List;

import com.workhardkj.entity.Todo;

public interface TodoRepositoryCustom {
	List<Todo> findAllTodosByUserId(Long id) throws Exception;
}

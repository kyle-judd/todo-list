package com.workhardkj.services;

import java.util.List;

import com.workhardkj.entity.Todo;

public interface TodoService {
	Todo findTodoById(Long id) throws Exception;
	List<Todo> findAllTodosByUserId(Long id);
	void saveTodo(Todo todo);
	void deleteTodo(Todo todo);
}

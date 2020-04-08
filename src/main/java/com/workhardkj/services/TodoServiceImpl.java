package com.workhardkj.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workhardkj.entity.Todo;
import com.workhardkj.repositories.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	@Override
	public Todo findTodoById(Long id) throws Exception {
		Optional<Todo> optionalTodo = todoRepository.findById(id);
		Todo retrievedTodo;
		if(optionalTodo.isPresent()) {
			retrievedTodo = optionalTodo.get();
		} else {
			throw new Exception("Todo with id of " + id + " could not be found!");
		}
		
		return retrievedTodo;
	}

	@Override
	public List<Todo> findAllTodosByUserId(Long id) {
		return todoRepository.findAll();
	}

	@Override
	public void saveTodo(Todo todo) {
		todoRepository.save(todo);
	}

	@Override
	public void deleteTodo(Todo todo) {
		todoRepository.delete(todo);
	}

}

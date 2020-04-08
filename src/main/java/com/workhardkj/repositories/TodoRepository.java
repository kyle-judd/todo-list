package com.workhardkj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workhardkj.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoRepositoryCustom {

}

package com.workhardkj.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.workhardkj.entity.Todo;

public class TodoRepositoryCustomImpl implements TodoRepositoryCustom {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Todo> findAllTodosByUserId(Long id) throws Exception {
		Session session = entityManager.unwrap(Session.class);
		Query<Todo> query = session.createQuery("from Todo where user=:id", Todo.class);
		query.setParameter("id", id);
		List<Todo> todosOfUser;
		try {
			todosOfUser = query.getResultList();
		} catch (Exception e) {
			todosOfUser = null;
		}
		return todosOfUser;
	}

}

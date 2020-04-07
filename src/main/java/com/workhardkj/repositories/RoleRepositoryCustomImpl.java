package com.workhardkj.repositories;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.workhardkj.entity.Role;


public class RoleRepositoryCustomImpl implements RoleRepositoryCustom {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public Optional<Role> findRoleByType(String roleType) {
		Session session = entityManager.unwrap(Session.class);
		Query<Role> query = session.createQuery("from Role where type=:roleType", Role.class);
		query.setParameter("roleType", roleType);
		return Optional.ofNullable(query.getSingleResult());
	}

}

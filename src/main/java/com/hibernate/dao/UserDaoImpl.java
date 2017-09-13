package com.hibernate.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hibernate.model.User;


@Transactional
@Repository
public class UserDaoImpl {
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public List<User> getAllUsers()
	{
		//String hql = "select u.userId userId, u.userName userName, u.password password, u.email email from User u";
		String hql = "from User";
		return (List<User>) entityManager.createQuery(hql).getResultList();
	}
	
}

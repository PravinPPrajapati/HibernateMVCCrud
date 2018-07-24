package com.hibernate.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		List<User> allUsers = new ArrayList<User>();
		
		// Way-1
		String hql = "from User";
		allUsers = (List<User>) entityManager.createQuery(hql).getResultList();
		
		// Way-2
		/*String hql = "select userId, userName, password, email from User";
		Query qry = entityManager.createQuery(hql);
		List<Object[]> l =qry.getResultList();
		for (Object[] row : l) {
			System.out.println("User values : " + row[0] + row[1] + row[2] + row[3]);
			User user = new User(Integer.parseInt(row[0].toString()), row[1].toString(), row[2].toString(),
					row[3].toString());
			System.out.println(user.getUserId() + user.getUserName() + user.getPassword() + user.getEmail());
			allUsers.add(user);
		}*/
		
		// Way-3
		/*String hql = "select new map(userId, userName, password, email) from User";
		Query qry = entityManager.createQuery(hql);
		List<Object[]> l =qry.getResultList();
		for (Object object : l) {
			Map map = (Map) object;
			User user = new User(Integer.parseInt(map.get("0").toString()), map.get("1").toString(),
					map.get("2").toString(), map.get("3").toString());
			System.out.println(user.getUserId() + user.getUserName() + user.getPassword() + user.getEmail());
			allUsers.add(user);
		}*/
		
		return allUsers;
	}
	
	public User getUserById(int userId) {
		return entityManager.find(User.class, userId);
	}
	
	public void deleteUser(int userId) {
		entityManager.remove(getUserById(userId));
	}
	
	public void insertUser(User user)
	{
		entityManager.persist(user);
	}
	
	public User updateUser(User user) {
		entityManager.flush();
	
		return user;
	}
	
}

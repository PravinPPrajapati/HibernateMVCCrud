package com.hibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hibernate.dao.UserDaoImpl;
import com.hibernate.model.User;


@Controller
@RequestMapping("/HibernateMVCCrud")
public class UserImpl {

	@Autowired
	private UserDaoImpl userDao = null;
	List<User> allUsers = null;
	
	@RequestMapping("/")
	public String getAllUsers(Model model)
	{
		allUsers = userDao.getAllUsers();
		model.addAttribute("allUsers", allUsers);
        return "manageUser";
	}
}

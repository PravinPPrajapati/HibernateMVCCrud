package com.hibernate.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hibernate.dao.UserDaoImpl;
import com.hibernate.model.User;


@Controller
@RequestMapping("/HibernateMVCCrud")
public class UserImpl {

	@Autowired
	private UserDaoImpl userDao = null;
	List<User> allUsers = null;
	
	// url : http://localhost:8080/HibernateMVCCrud/
	@RequestMapping("/")
	public String getAllUsers(Model model)
	{
		allUsers = userDao.getAllUsers();
		model.addAttribute("allUsers", allUsers);
        return "manageUser";
	}
	
	@RequestMapping("/{userId}")
	public String getUserById(Model model, @PathVariable("userId") int userId)
	{
		System.out.println("User id="+userId);
		User user = userDao.getUserById(userId);
		model.addAttribute("allUsers", user);
		return "manageUser";
	}
	
	@RequestMapping("/deleteUser")
	public String deleteUser(Model model,@RequestParam(value="id", required=false, defaultValue="0") int userId)
	{
		userDao.deleteUser(userId);
		
		allUsers = userDao.getAllUsers();
		model.addAttribute("allUsers", allUsers);
		return "manageUser";
	}
	
	@GetMapping("/insertUser")
	public String insertUserPre(Model model)
	{
		model.addAttribute("user", new User());
		return "insertUser";
	}

	@PostMapping(value="/inserUserObject")
	public String insertUser(@ModelAttribute User user, Model model,@RequestParam Map<String,String> paramMap /*, 
			@RequestParam(value="userName") String userName1, @RequestParam(value="password") String password1, @RequestParam(value="userEmail") String email1*/)
	{
		//int id = Integer.parseInt(request.getParameter("id"));
		String userName=paramMap.get("userName");
		String password=paramMap.get("password");
		String email=paramMap.get("email");

		//User user = new User(userName,password,email);
		userDao.insertUser(user);
		
		allUsers = userDao.getAllUsers();
		model.addAttribute("allUsers", allUsers);
		return "manageUser";
	}
	

	@GetMapping("/updateUser")
	public String updateUserPre(Model model,@RequestParam(value="id", required=false, defaultValue="0") int userId)
	{
		System.out.println("Pre user id = "+userId);
		User user = userDao.getUserById(userId);
		
		model.addAttribute("user", user);
		return "updateUser";
	}
	
	@PostMapping(value="/updateUserObject")
	public String updateUser(@ModelAttribute User user, Model model,@RequestParam Map<String,Object> paramMap /*, 
			@RequestParam(value="userName") String userName1, @RequestParam(value="password") String password1, @RequestParam(value="userEmail") String email1*/)
	{
		
		// Working 
		//Integer id = (Integer) paramMap.get("userId");
		String userName=(String) paramMap.get("userName");
		String password=(String) paramMap.get("password");
		String email=(String) paramMap.get("email");
		
		User updatedUser = userDao.getUserById(user.getUserId());
		updatedUser.setUserName(userName);
		updatedUser.setPassword(password);
		updatedUser.setEmail(email);
		
		userDao.updateUser(updatedUser);
		
		
		// Not working, do not know why
		/*User updatedUser = userDao.getUserById(user.getUserId());
		updatedUser = new User(user.getUserName(),user.getPassword(),user.getEmail()); 
		userDao.updateUser(updatedUser);*/
		
		allUsers = userDao.getAllUsers();
		model.addAttribute("allUsers", allUsers);
		return "manageUser";
	}
	
}

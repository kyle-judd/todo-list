package com.workhardkj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.workhardkj.entity.User;
import com.workhardkj.services.TodoService;
import com.workhardkj.services.UserService;

@Controller
public class NavController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping("/")
	public String landingPage() {
		return "index";
	}
	
	@GetMapping("/home")
	public String home(Model model) {
		User loggedInUser = getLoggedInUser();
		model.addAttribute("allTodos", todoService.findAllTodosByUserId(loggedInUser.getId()));
		return "home";
	}
	
	private User getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userService.findUserByUsername(auth.getName());
	}
}

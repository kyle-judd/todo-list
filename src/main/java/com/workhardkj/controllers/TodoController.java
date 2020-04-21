package com.workhardkj.controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.workhardkj.entity.Todo;
import com.workhardkj.entity.User;
import com.workhardkj.services.TodoService;
import com.workhardkj.services.UserService;

@Controller
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@Autowired
	private UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, false));
	}
	
	@GetMapping("/addTodo")
	public String createTodo(Model model) {
		model.addAttribute("newtodo", new Todo());
		return "add-todo";
	}
	
	@PostMapping("/saveTodo")
	public String saveTodo(@ModelAttribute("newtodo") Todo todo) {
		User loggedInUser = getLoggedInUser();
		todo.setUser(loggedInUser);
		todoService.saveTodo(todo);
		return "redirect:/";
	}
	
	@GetMapping("/deleteTodo")
	public String deleteTodo(@RequestParam("todoId") Long id) {
		todoService.deleteTodo(id);
		return "redirect:/";
	}
	
	@GetMapping("/updateTodo")
	public String updateTodo(@RequestParam("todoId") Long id, Model model) throws Exception {
		Todo todo = todoService.findTodoById(id);
		model.addAttribute("newtodo", todo);
		return "add-todo";
	}
	
	private User getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User loggedInUser = userService.findUserByUsername(auth.getName());
		return loggedInUser;
	}
}

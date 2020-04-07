package com.workhardkj.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.workhardkj.entity.User;
import com.workhardkj.services.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/registration-form")
	public String showRegistrationForm(Model model) {
		model.addAttribute("newUser", new User());
		return "registration-form";
	}
	
	@PostMapping("/process-registration-form")
	public String processRegistrationForm(@ModelAttribute("new-user") User newUser, BindingResult bindingResult, Model model) throws Exception {
		if(bindingResult.hasErrors()) {
			return "registration-form";
		} else {
			Optional<User> optionalUser = Optional.ofNullable(userService.findUserByUsername(newUser.getUsername()));
			if(optionalUser.isPresent()) {
				model.addAttribute("userExists", "Username already exists!");
				model.addAttribute("newUser", new User());
				return "registration-form";
			} else {
				userService.save(newUser);
				return "registration-success";
			}
		}
	}
}

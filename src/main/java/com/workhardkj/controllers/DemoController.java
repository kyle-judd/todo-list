package com.workhardkj.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
	
	@GetMapping("/helloWorld")
	public String helloWorld() {
		return "Hello World!";
	}
}

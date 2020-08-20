package com.ywmobile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeContoller {
	@GetMapping("/helloworld")
	public String welcome(String name, int age, Model model) {
		System.out.println("Name is " + name + ", age is " + age + ".");
		model.addAttribute("name", name);
		model.addAttribute("age", age);

		return "welcome";
	}
}

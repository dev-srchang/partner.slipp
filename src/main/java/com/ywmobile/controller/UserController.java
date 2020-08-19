package com.ywmobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ywmobile.domain.User;
import com.ywmobile.repository.UserRepository;

@Controller
public class UserController {
//	private List<User> users = new ArrayList<User>();

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/user/create")
	public String create(User user) {
		System.out.println("user : " + user.toString());
//		users.add(user);
		userRepository.save(user);

		return "redirect:/user/list";
	}

	@GetMapping("/user/list")
	public String list(Model model) {
//		model.addAttribute("users", users);
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}

	// srchang - add form
	@GetMapping("/user/form")
	public String form() {
		return "/user/form";
	}
}

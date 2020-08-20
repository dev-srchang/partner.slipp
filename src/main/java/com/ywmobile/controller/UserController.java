package com.ywmobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ywmobile.domain.User;
import com.ywmobile.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(User user) {
		System.out.println("user : " + user.toString());
		userRepository.save(user);
		return "redirect:/user/list"; // call list method
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form() {
		return "/user/form";
	}

	@RequestMapping(value = "/{id}/form", method = RequestMethod.GET)
	public String updateForm(@PathVariable Long id, Model model) {
		User user = userRepository.findById(id).orElse(null);
//		System.out.println("User : " + user.toString());
		model.addAttribute("user", user);
		return "/user/updateForm";
	}

	@RequestMapping(value = "/{id}", method = {RequestMethod.POST, RequestMethod.PUT})
	public String update(@PathVariable Long id, User newUser) {
		User user = userRepository.findById(id).orElse(null);
		user.update(newUser);
//		System.out.println("new user : " + user.toString());
		userRepository.save(user);
		return "redirect:/user/list"; // call list method
	}
}

package com.ywmobile.controller;

import javax.servlet.http.HttpSession;

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

	@RequestMapping(value = "/create", method = RequestMethod.POST) // Action : registerForm 에서 "등록" 버튼을 눌렀을 시
	public String create(User user) {
		System.out.println("user : " + user.toString());
		userRepository.save(user);
		return "redirect:/user/listTable"; // Result : redirect to listTable.html
	}

	@RequestMapping(value = "/listTable", method = RequestMethod.GET) // Action : navbar 에서 "사람 모양" 아이콘 메뉴를 눌렀을 시
	public String listTable(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/listTable"; // Result : move to listTable.html
	}

	@RequestMapping(value = "/registerForm", method = RequestMethod.GET) // Action : navbar에서 "파트너사 등록" 메뉴 버튼을 눌렀을 시
	public String registerForm() {
		return "/user/registerForm"; // Result : move to registerForm.html
	}

	@RequestMapping(value = "/{id}/updateForm", method = RequestMethod.GET) // Action : listTable 에서 "수정" 버튼을 눌렀을 시
	public String updateForm(@PathVariable Long id, Model model) {
		User user = userRepository.findById(id).orElse(null);
//		System.out.println("User : " + user.toString());
		model.addAttribute("user", user);
		return "/user/updateForm"; // Result : move to updateForm.html
	}

	@RequestMapping(value = "/{id}", method = {RequestMethod.POST, RequestMethod.PUT}) // Action : updateForm 에서 "수정 완료" 버튼을 눌렀을 시
	public String update(@PathVariable Long id, User newUser) {
		User user = userRepository.findById(id).orElse(null);
		user.update(newUser);
//		System.out.println("new user : " + user.toString());
		userRepository.save(user);
		return "redirect:/user/listTable"; // Result : move to listTable.html
	}

	@RequestMapping(value = "/loginForm", method = RequestMethod.GET) // Action : navbar 에서 "로그인" 메뉴 버튼 을 눌렀을 시
	public String loginForm() {
		return "/user/loginForm"; // Result : move to loginForm.html
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST) // Action : loginForm 에서 "로그인" 버튼을 눌렀을 시
	public String login(String partnerId, String pwd, HttpSession httpSession) {
		User user = userRepository.findByPartnerId(partnerId);

		if (userRepository.findByPartnerId(partnerId) == null) {
			System.out.println("Login failure 1");
			return "redirect:/user/loginForm"; // Result : move to loginForm.html
		}

		if (!pwd.equals(user.getPwd())) {
			System.out.println("Login failure 2");
			return "redirect:/user/loginForm"; // Result : move to loginForm.html
		}

		System.out.println("Login success");

		// 세션에 저장 로직
		httpSession.setAttribute("user", user);

		return "redirect:/"; // Result : move to index.html
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET) // Action : navbar 에서 "로그아웃" 메뉴 버튼 을 눌렀을 시
	public String logout(HttpSession httpSession) {
		// 세션에 저장된 데이터를 삭제
		httpSession.removeAttribute("user");

		return "redirect:/"; // Result : move to index.html
	}
}

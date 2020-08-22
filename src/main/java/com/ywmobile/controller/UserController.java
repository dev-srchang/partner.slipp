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
import com.ywmobile.util.HttpSessionUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	// Action : registerForm 에서 "등록" 버튼을 눌렀을 시
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(User user) {
//		System.out.println("user : " + user.toString());
		userRepository.save(user);
		
		return "redirect:/user/listTable"; // Result : redirect to listTable.html
	}

	// Action : navbar 에서 "사람 모양" 아이콘 메뉴를 눌렀을 시
	@RequestMapping(value = "/listTable", method = RequestMethod.GET)
	public String listTable(Model model) {
		model.addAttribute("users", userRepository.findAll());
		
		return "/user/listTable"; // Result : move to listTable.html
	}

	// Action : navbar에서 "파트너사 등록" 메뉴 버튼을 눌렀을 시
	@RequestMapping(value = "/registerForm", method = RequestMethod.GET)
	public String registerForm() {
		return "/user/registerForm"; // Result : move to registerForm.html
	}

	// Action : listTable 에서 "수정" 버튼을 눌렀을 시 & navbar 에서 "개인 정보 수정" 메뉴 버튼 을 눌렀을 시
	@RequestMapping(value = "/{id}/updateForm", method = RequestMethod.GET)
	public String updateForm(@PathVariable Long id, Model model, HttpSession httpSession) {
		// 로그인한 세션을 가진 사용자인지 판단
		if (!HttpSessionUtil.isLoginUser(httpSession)) {
			return "redirect:/user/loginForm"; // Result : move to loginForm.html
		}
		
		User sessionedUser = HttpSessionUtil.getUserFromSession(httpSession);
		if (!sessionedUser.matchId(id)) {
			return "redirect:/user/logout"; // Result : move to index.html
//			throw new IllegalStateException("You can't update another user.");
		}
		
		User user = userRepository.findById(id).orElse(null);
		model.addAttribute("user", user);
		
		return "/user/updateForm"; // Result : move to updateForm.html
	}

	// Action : updateForm 에서 "수정 완료" 버튼을 눌렀을 시
	@RequestMapping(value = "/{id}", method = {RequestMethod.POST, RequestMethod.PUT})
	public String update(@PathVariable Long id, User updatedUser, HttpSession httpSession) {
		// 로그인한 세션을 가진 사용자인지 판단
		if (!HttpSessionUtil.isLoginUser(httpSession)) {
			return "redirect:/user/loginForm"; // Result : move to loginForm.html
		}

		User sessionedUser = HttpSessionUtil.getUserFromSession(httpSession);
		if (!sessionedUser.matchId(id)) {
			return "redirect:/user/loginForm"; // Result : move to loginForm.html
//			throw new IllegalStateException("You can't update another user.");
		}
		
		User user = userRepository.findById(id).orElse(null);
		user.update(updatedUser);
		userRepository.save(user);
		
		return "redirect:/user/listTable"; // Result : move to listTable.html
	}

	// Action : navbar 에서 "로그인" 메뉴 버튼 을 눌렀을 시
	@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	public String loginForm() {
		return "/user/loginForm"; // Result : move to loginForm.html
	}

	// Action : loginForm 에서 "로그인" 버튼을 눌렀을 시
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String partnerId, String pwd, HttpSession httpSession) {
		User user = userRepository.findByPartnerId(partnerId);

		if (userRepository.findByPartnerId(partnerId) == null) {
			System.out.println("Login failure - 1");
			return "redirect:/user/loginForm"; // Result : move to loginForm.html
		}

		if (!user.matchPwd(pwd)) {
			System.out.println("Login failure - 2");
			return "redirect:/user/loginForm"; // Result : move to loginForm.html
		}
		
		// 세션에 저장 로직
		httpSession.setAttribute(HttpSessionUtil.USER_SESSION_KEY, user);

		return "redirect:/"; // Result : move to index.html
	}

	// Action : navbar 에서 "로그아웃" 메뉴 버튼 을 눌렀을 시
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession httpSession) {
		// 세션에 저장된 데이터를 삭제
		httpSession.removeAttribute(HttpSessionUtil.USER_SESSION_KEY);
		
		return "redirect:/"; // Result : move to index.html
	}
}

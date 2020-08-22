package com.ywmobile.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ywmobile.domain.Question;
import com.ywmobile.domain.User;
import com.ywmobile.repository.QuestionRepository;
import com.ywmobile.util.HttpSessionUtil;

@Controller
@RequestMapping("/question")
public class QuestionController {
	@Autowired
	private QuestionRepository questionRepository;
	
	// Action : index 에서 질문하기 눌렀을 시
	@RequestMapping(value = "/questionForm", method = RequestMethod.GET)
	public String qeustionForm(HttpSession httpSession) {
		// 로그인한 세션을 가진 사용자인지 판단
		if (!HttpSessionUtil.isLoginUser(httpSession)) {
			return "redirect:/user/loginForm"; // Result : move to loginForm.html
		}
		
		return "/qna/questionForm"; // Result : move to questionForm.html
	}
	
	// Action : questionForm 에서 "질문 등록" 버튼을 눌렀을 시
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(String title, String contents, HttpSession httpSession) {
		// 로그인한 세션을 가진 사용자인지 판단
		if (!HttpSessionUtil.isLoginUser(httpSession)) {
			return "redirect:/user/loginForm"; // Result : move to loginForm.html
		}

		User sessionUser = HttpSessionUtil.getUserFromSession(httpSession);
		Question question = new Question(sessionUser.getPartnerName(), title, contents);
		questionRepository.save(question);
				
		return "redirect:/"; // Result : redirect to index.html
	}
}

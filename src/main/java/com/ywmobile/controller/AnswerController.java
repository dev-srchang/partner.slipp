package com.ywmobile.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ywmobile.domain.Answer;
import com.ywmobile.domain.Question;
import com.ywmobile.domain.User;
import com.ywmobile.repository.AnswerRepository;
import com.ywmobile.repository.QuestionRepository;
import com.ywmobile.util.HttpSessionUtil;

@Controller
@RequestMapping("/question/{questionId}/answer")
public class AnswerController {
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	// Action : showQuestion.html 에서 답변 달기 눌렀을 시
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(@PathVariable Long questionId, String contents, HttpSession httpSession) {
		
		// 로그인한 세션을 가진 사용자인지 판단
		if (!HttpSessionUtil.isLoginUser(httpSession)) {
			return "redirect:/user/loginForm"; // Result : move to loginForm.html
		}
		
		User loginUser = HttpSessionUtil.getUserFromSession(httpSession);
		Question question = questionRepository.findById(questionId).orElse(null);
		Answer answer = new Answer(loginUser, question, contents);
		
		answerRepository.save(answer);
		
		return String.format("redirect:/question/%d", questionId); // Result : move to questionForm/questionId
	}
}

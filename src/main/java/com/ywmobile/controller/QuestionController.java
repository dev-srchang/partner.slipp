package com.ywmobile.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
		Question question = new Question(sessionUser, title, contents);
		questionRepository.save(question);
				
		return "redirect:/"; // Result : redirect to index.html
	}
	
	// Action : index 에서 자신이 질문한 내용의 title 을 눌렀을 시
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("question", questionRepository.findById(id).orElse(null));
		
		return "/qna/showQuestion"; // Result : move to showQuestion.html
	}
	
	// Action : showQuestion 에서 "수정" 버튼을 눌렀을 시
	@RequestMapping(value = "/{id}/updateForm", method = RequestMethod.GET)
	public String updateForm(@PathVariable Long id, Model model) {
		model.addAttribute("question", questionRepository.findById(id).orElse(null));
		
		return "/qna/updateForm"; // Result : move to updateForm.html
	}
	
	// Action : updateForm 에서 "수정 완료" 버튼을 눌렀을 시
	@RequestMapping(value = "/{id}", method = {RequestMethod.POST, RequestMethod.PUT})
	public String update(@PathVariable Long id, String title, String contents) {
		Question question = questionRepository.findById(id).orElse(null);
		question.update(title, contents);
		questionRepository.save(question);
		
		return String.format("redirect:/question/%d", id); // Result : move to showQuestion.html
	}
	
	// Action : showQuestion 에서 "삭제" 버튼을 눌렀을 시
	@RequestMapping(value = "/delete/{id}", method = {RequestMethod.POST, RequestMethod.DELETE})
	public String delete(@PathVariable Long id) {
		questionRepository.deleteById(id);
		
		return "redirect:/"; // Result : move to index.html
	}
}

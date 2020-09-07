package com.ywmobile.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ywmobile.domain.Answer;
import com.ywmobile.domain.Question;
import com.ywmobile.domain.User;
import com.ywmobile.refer.CodeDefine;
import com.ywmobile.refer.Result;
import com.ywmobile.repository.AnswerRepository;
import com.ywmobile.repository.QuestionRepository;
import com.ywmobile.util.HttpSessionUtil;

@RestController
@RequestMapping("/api/question/{questionId}/answer")
public class ApiAnswerController {
	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private QuestionRepository questionRepository;

	// Action : showQuestion.html 에서 답변 달기 눌렀을 시
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Answer create(@PathVariable Long questionId, String contents, HttpSession httpSession) {

		// 로그인한 세션을 가진 사용자인지 판단
		if (!HttpSessionUtil.isLoginUser(httpSession)) {
			return null;
		}

		User loginUser = HttpSessionUtil.getUserFromSession(httpSession);
		Question question = questionRepository.findById(questionId).orElse(null);
		Answer answer = new Answer(loginUser, question, contents);

		return answerRepository.save(answer);
	}

	@DeleteMapping("/{id}")
	public Result delete(@PathVariable Long questionId, @PathVariable Long id, HttpSession httpSession) {

		if (!HttpSessionUtil.isLoginUser(httpSession)) {
			return Result.fail(CodeDefine.ErrMsg.ERR_MSG_NEED_TO_LOGIN);
		}

		Answer answer = answerRepository.findById(id).orElse(null);
		User loginUser = HttpSessionUtil.getUserFromSession(httpSession);

		if (!answer.isSameWriter(loginUser)) {
			return Result.fail(CodeDefine.ErrMsg.ERR_MSG_ALLOW_FOR_OWNER);
		}

		answerRepository.deleteById(id);

		return Result.success();
	}
}

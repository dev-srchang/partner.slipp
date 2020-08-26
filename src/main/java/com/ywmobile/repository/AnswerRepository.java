package com.ywmobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ywmobile.domain.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}

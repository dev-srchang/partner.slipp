package com.ywmobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ywmobile.domain.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}

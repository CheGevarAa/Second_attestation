package com.popov.popov.repo;

import com.popov.popov.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepo extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestionId(Long questionId);
}

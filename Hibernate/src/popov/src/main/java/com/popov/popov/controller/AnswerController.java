package com.popov.popov.controller;

import com.popov.popov.exception.ResourceNotFoundException;
import com.popov.popov.model.Answer;
import com.popov.popov.repo.AnswerRepo;
import com.popov.popov.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AnswerController {

    @Autowired
    private AnswerRepo answerRepo;

    @Autowired
    private QuestionRepo questionRepo;

    @GetMapping("/questions/{questionId}/answers")
    public List<Answer> getAnswerByQuestion(@PathVariable Long questionId) {
        return answerRepo.findByQuestionId(questionId);
    }

    @PostMapping("/questions/{questionId}/answers")
    public Answer addAnswer(@PathVariable Long questionId, @Valid @RequestBody Answer answer){
        return questionRepo.findById(questionId)
                .map(question -> {
                    answer.setQuestion(question);
                    return answerRepo.save(answer);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found: "+questionId));
    }

    @PutMapping("/questions/{questionId}/answers/{answerId}")
    public Answer updateAnswer(@PathVariable Long questionId, @PathVariable Long answerId, @Valid @RequestBody Answer answerRequest){
        if(!questionRepo.existsById(questionId)){
            throw new ResourceNotFoundException("Question not found: "+questionId);
        }
        return answerRepo.findById(answerId)
                .map(answer -> {
                    answer.setDescription(answerRequest.getDescription());
                    return answerRepo.save(answer);
                }).orElseThrow(() -> new ResourceNotFoundException("Answer not found: "+answerId));
    }

    @DeleteMapping("/questions/{questionId}/answers/{answerId}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long questionId, @PathVariable Long answerId){
        if(!questionRepo.existsById(questionId)){
            throw new ResourceNotFoundException("Question not found: "+questionId);
        }
        return answerRepo.findById(answerId)
                .map(answer -> {
                    answerRepo.delete(answer);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Answer not found: "+answerId));
    }

}

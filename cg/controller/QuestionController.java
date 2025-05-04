package com.cg.controller;

import com.cg.dto.QuestionDto;
import com.cg.entities.Question;

import com.cg.service.QuestionService;

import jakarta.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")

public class QuestionController {
	

    @Autowired
    private QuestionService questionService;
    
   

    @PostMapping("/addQuestion")
    public ResponseEntity<Question> addQuestion(@Valid @RequestBody  Question question) {
        return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.CREATED);
    }

    @GetMapping("/viewQuestionById/{questionId}")
    public ResponseEntity<QuestionDto> getQuestionById(@PathVariable String questionId) {
        return new ResponseEntity<>(questionService.getQuestionById(questionId), HttpStatus.OK);
    }
 

    @GetMapping("/viewAllQuestions")
    public ResponseEntity<List<QuestionDto>> getAllQuestions() {
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    @PutMapping("/updateQuestion/{questionId}")
    public ResponseEntity<Question> updateQuestion(@Valid @PathVariable String questionId, @RequestBody Question question) {
        return new ResponseEntity<>(questionService.updateQuestion(questionId, question), HttpStatus.OK);
    }

    @DeleteMapping("/deleteQuestion/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable String questionId) {
        questionService.deleteQuestion(questionId);
        return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/searchQuestion/{keyword}")
    public ResponseEntity<List<Question>> searchQuestionsByKeyword(@PathVariable String keyword) {
        return new ResponseEntity<>(questionService.searchQuestionsByKeyword(keyword), HttpStatus.OK);
    }
    
    @GetMapping("/searchByQuizId/{quizId}")
    public ResponseEntity<List<QuestionDto>> searchQuestionsByQuizId(@PathVariable String quizId) {
        return new ResponseEntity<>(questionService.searchQuestionsByQuizId(quizId), HttpStatus.OK);
    }
    
    

} 

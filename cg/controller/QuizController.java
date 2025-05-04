package com.cg.controller;

import com.cg.dto.CourseDto;
import com.cg.dto.QuizSubmissionDto;
import com.cg.entities.Quiz;
import com.cg.exception.QuizNotFoundException;
import com.cg.service.QuizService;

import com.cg.dto.CourseDto;
import com.cg.microservicecalls.CourseClient;

import jakarta.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/quizzes")

public class QuizController {
  
	
    
    @Autowired
    private QuizService quizService;
    
    
    @Autowired
	CourseClient courseClient;

    @PostMapping("/addQuiz")
    public ResponseEntity<Quiz> createQuiz(@Valid @RequestBody Quiz quiz) {
        return new ResponseEntity<Quiz>(quizService.createQuiz(quiz), HttpStatus.CREATED);
    }

    @GetMapping("/viewQuizById/{quizId}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable String quizId) throws QuizNotFoundException {
        return new ResponseEntity<>(quizService.getQuizById(quizId), HttpStatus.OK);
    }



    @GetMapping("/viewAllQuizzes")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return new ResponseEntity<>(quizService.getAllQuizzes(), HttpStatus.OK);
        
        
    }
 
    
    @GetMapping("viewAllQuizzesByCourseId")
    public ResponseEntity <List<Quiz>> getAllQuizzesByCourseId(){
    	return new ResponseEntity<>(quizService.getAllQuizzesByCourseId(),HttpStatus.OK);
    	
    }
      
//    
//    @GetMapping("/viewAllQuizzesByPage")
//    public ResponseEntity<Page<Quiz>> getAllQuizzesByPage(
//            @RequestParam(defaultValue = "0") int page, 
//            @RequestParam(defaultValue = "5") int size) {
//        Page<Quiz> paginatedQuizzes = quizService.getAllQuizzesByPage(page, size);
//        return ResponseEntity.ok(paginatedQuizzes);
//    }


    @PutMapping("/updateQuiz/{quizId}")
    public ResponseEntity<Quiz> updateQuiz(@Valid @PathVariable String quizId, @RequestBody Quiz quiz) throws QuizNotFoundException {
        return new ResponseEntity<>(quizService.updateQuiz(quizId, quiz), HttpStatus.OK);
    }

    @DeleteMapping("/deleteQuiz/{quizId}")
    public ResponseEntity<String> deleteQuiz(@PathVariable String quizId) throws QuizNotFoundException {
        quizService.deleteQuiz(quizId);
        return new ResponseEntity<>("Quiz deleted successfully", HttpStatus.OK);
    }
    
    
    @PostMapping("/evaluate")
    public ResponseEntity<String> evaluateQuiz(@Valid @RequestBody QuizSubmissionDto submission) {
        int marks = quizService.evaluateQuiz(submission);
        String resultMessage = "User " + submission.getUserId() + " scored " + marks + " marks in the quiz.";
        return ResponseEntity.ok(resultMessage);
    }
    
    @GetMapping("/searchcoursebyId/{courseId}")
    public ResponseEntity<CourseDto> getCourse(@PathVariable("courseId") String courseId){
    	return ResponseEntity.ok(courseClient.getCourseById(courseId));
    }
    
    @GetMapping("/getAllCourses")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        return ResponseEntity.ok(courseClient.getAllCourses());
    }

}

package com.cg.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cg.dto.QuestionDto;
import com.cg.dto.QuizSubmissionDto;
import com.cg.entities.Question;
import com.cg.entities.Quiz;

public interface QuizService {
	    Quiz createQuiz(Quiz quiz);
	    Quiz getQuizById(String quizId);
	    List<Quiz> getAllQuizzes();
	    Quiz updateQuiz(String quizId, Quiz quiz);
	    void deleteQuiz(String quizId);
	   
	
	    
	    List<Quiz> getAllQuizzesByCourseId();

	    int evaluateQuiz(QuizSubmissionDto submission);
	   
//		Page<Quiz> getAllQuizzesByPage(int page, int size);
	    
	   
		
}

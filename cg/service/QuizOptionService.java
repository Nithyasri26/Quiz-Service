package com.cg.service;

import java.util.List;

import com.cg.entities.QuizOption;

public interface QuizOptionService {
	    QuizOption addQuizOption(QuizOption quizOption);
	    List<QuizOption> getAllQuizOptions();
	    List<QuizOption> getOptionsByQuestionId(String questionId);
	    QuizOption updateQuizOption(String optionId, QuizOption updatedOption);
	    void deleteQuizOption(String optionId);
	   
}
